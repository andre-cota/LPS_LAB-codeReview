package com.lps.api.dtos;

public record PurchaseRequestDTO(
    Long studentId,
    Integer price, 
    Integer quantity,
    Long advantageId
) {
    
}
