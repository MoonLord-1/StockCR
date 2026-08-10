package com.yeirel.StockCR.dto.merchant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateRequest (
        @NotBlank
        @Size(min = 3, max = 150)
        String businessName
){
}
