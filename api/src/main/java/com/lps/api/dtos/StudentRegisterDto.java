package com.lps.api.dtos;

import com.lps.api.models.Address;

public record StudentRegisterDto(
        String name,
        String email,
        String password,
        String cpf,
        Double balance,
        String rg,
        Long courseId,
        Address address) {

}
