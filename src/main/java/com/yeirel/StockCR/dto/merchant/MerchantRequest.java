package com.yeirel.StockCR.dto.merchant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MerchantRequest(@NotBlank
                              @Size(min = 3, max = 150)
                              String businessName,

                              @NotBlank
                              @Email
                              String email,

                              @NotBlank
                              @Size(min = 8, max = 72)
                              String password) {
}
