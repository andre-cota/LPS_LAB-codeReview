package com.lps.api.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String userType;
    private Long id;
    @Builder.Default
    private String tokenType = "Bearer";

    public LoginResponse(String accessToken, String userType, Long id) {
        this.accessToken = accessToken;
        this.userType = userType;
        this.id = id;
        this.tokenType = "Bearer";
    }
}