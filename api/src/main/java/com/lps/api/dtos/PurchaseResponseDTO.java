package com.lps.api.dtos;

import java.time.LocalDate;

public record PurchaseResponseDTO(
    Long id,
    LocalDate date,
    Integer price
) {
    
}
