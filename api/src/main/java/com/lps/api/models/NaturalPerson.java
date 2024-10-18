package com.lps.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class NaturalPerson extends User {

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "balance", nullable = false)
    private Double balance;

    public NaturalPerson(String name, String email, String password, String cpf, Double balance) {
        super(name, email, password);
        this.cpf = cpf;
        this.balance = balance;
    }

}
