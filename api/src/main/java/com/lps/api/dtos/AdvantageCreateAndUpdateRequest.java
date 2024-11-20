package com.lps.api.dtos;

public record AdvantageCreateAndUpdateRequest(
    String name,
    String description,
    Integer value, 
    String urlImage,
    Long companyId
) {
    
}
