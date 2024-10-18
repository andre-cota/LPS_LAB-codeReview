package com.lps.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student extends NaturalPerson {

    @Column(name = "rg", nullable = false, unique = true)
    private String rg;

    @Column(name = "course", nullable = false)
    private String course;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private Address address;

    public Student(String name, String email, String password, String cpf, Double balance, String rg, String course) {
        super(name, email, password, cpf, balance);
        this.rg = rg;
        this.course = course;
    }
}
