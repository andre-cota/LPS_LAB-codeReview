package com.lps.api.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchases> purchases;

    public Student(String name, String email, String password, String cpf, Double balance, String rg, Course course) {
        super(name, email, password, cpf, balance);
        this.rg = rg;
        this.course = course;
    }
}
