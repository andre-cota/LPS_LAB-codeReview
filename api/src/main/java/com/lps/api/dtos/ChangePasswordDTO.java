package com.lps.api.dtos;

public record ChangePasswordDTO(String token, String email, String novaSenha, String confirmacaoNovaSenha) {

    public boolean isPasswordConfirmed() {
        return novaSenha.equals(confirmacaoNovaSenha);
    }
}
