package com.lps.api.dtos;

import com.lps.api.models.Address;
import com.lps.api.models.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class StudentResponseDTO {
    private String name;
    private String email;
    private String cpf;
    private Double balance;
    private String rg;
    private Course course;
    private Address address;

    public StudentResponseDTO(String name, String email, String cpf, Double balance, String rg, Course course, Address address) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.balance = balance;
        this.rg = rg;
        this.course = course;
        this.address = address;
    }
}
