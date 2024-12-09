package com.lps.api.dtos;

import java.util.Objects;

public record ForgetPasswordDTO(String email) {

    public ForgetPasswordDTO {
        Objects.requireNonNull(email, "O e-mail não pode ser nulo");
        if (email.isBlank()) {
            throw new IllegalArgumentException("O e-mail não pode estar vazio");
        }
    }
}