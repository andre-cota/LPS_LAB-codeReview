package com.lps.api.dtos;

public record ChangePasswordDTO(String token, String email, String novaSenha, String confirmacaoNovaSenha) {
}
