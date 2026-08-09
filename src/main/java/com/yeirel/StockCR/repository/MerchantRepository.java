package com.yeirel.StockCR.repository;

import com.yeirel.StockCR.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Optional<Merchant> findBySlug(String slug);
    Optional<Merchant> findByEmail(String email);
    boolean existsBySlug(String slug);
}
