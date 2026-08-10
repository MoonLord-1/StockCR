package com.yeirel.StockCR.dto.merchant;

public record MerchantResponse(
        Long id,
        String businessName,
        String slug,
        String email,
        boolean active
) {
    public static MerchantResponse from(com.yeirel.StockCR.entities.Merchant merchant) {
        return new MerchantResponse(
                merchant.getId(),
                merchant.getBusinessName(),
                merchant.getSlug(),
                merchant.getEmail(),
                merchant.isActive()
        );
}
}
