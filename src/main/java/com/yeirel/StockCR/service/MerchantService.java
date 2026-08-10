package com.yeirel.StockCR.service;

import com.yeirel.StockCR.dto.merchant.ChangePasswordRequest;
import com.yeirel.StockCR.dto.merchant.MerchantRequest;
import com.yeirel.StockCR.dto.merchant.MerchantResponse;
import com.yeirel.StockCR.entities.Merchant;
import com.yeirel.StockCR.exception.ConflictException;
import com.yeirel.StockCR.exception.InvalidCredentialsException;
import com.yeirel.StockCR.exception.ResourceNotFoundException;
import com.yeirel.StockCR.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final PasswordEncoder passwordEncoder;


    //Metodo exclusivo para el admin, con el objetivo de ver todos sus clientes y desde ahí poner cambiar el estado
    public List<MerchantResponse> findAll() {
        return merchantRepository.findAll().stream()
                .map(MerchantResponse::from)
                .toList();
    }

    //Este es otro metodo exclusivo del admin, para buscar comerciantes
    public MerchantResponse getMerchantById(Long merchantId) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(
                () -> new ResourceNotFoundException("Comerciante no encontrado: " + merchantId)
        );
        return MerchantResponse.from(merchant);
    }
    public MerchantResponse create(MerchantRequest request) {
        String slug = generateUniqueSlug(request.businessName());

        Merchant merchant = Merchant.builder()
                .businessName(request.businessName())
                .email(request.email())
                .slug(slug)
                .passwordHash(passwordEncoder.encode(request.password()))
                .build();

        Merchant saved = merchantRepository.save(merchant);
        return MerchantResponse.from(saved);
    }



    @Transactional
    public MerchantResponse update(Long merchantId, MerchantRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(
                () -> new ResourceNotFoundException("Comerciante no encontrado: " + merchantId)
        );
        merchant.setBusinessName(request.businessName());
        return MerchantResponse.from(merchant);
    }

    public Merchant findMerchantBySlug(String slug) {
        return merchantRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada: " + slug));
    }

    public Merchant findMerchantByEmail(String email) {
        return merchantRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Comerciante no encontrado: " + email));
    }
    @Transactional
    public void changePassword(Long merchantId, ChangePasswordRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(
                () -> new ResourceNotFoundException("Comerciante no encontrado: " + merchantId)
        );

        if (!passwordEncoder.matches(request.currentPassword(), merchant.getPasswordHash())) {
            throw new InvalidCredentialsException("La contraseña actual no coincide");
        }
        merchant.setPasswordHash(passwordEncoder.encode(request.newPassword()));
    }

    @Transactional
    public MerchantResponse changeStatus(Long merchantId, boolean active) {
        Merchant merchant =  merchantRepository.findById(merchantId).orElseThrow(
                () -> new ResourceNotFoundException("Comerciante no encontrado: " + merchantId)
        );
        merchant.setActive(active);
        return MerchantResponse.from(merchant);
    }

    private String generateUniqueSlug(String businessName) {
        String base = normalize(businessName);
        String slug = base;
        int suffix = 2;

        while (merchantRepository.existsBySlug(slug)) {
            slug = base + "-" + suffix;
            suffix++;
        }
        return slug;
    }

    private String normalize(String input) {
        String noAccents = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return Pattern.compile("[^a-zA-Z0-9\\s-]").matcher(noAccents)
                .replaceAll("")
                .trim()
                .toLowerCase()
                .replaceAll("\\s+", "-");
    }


}
