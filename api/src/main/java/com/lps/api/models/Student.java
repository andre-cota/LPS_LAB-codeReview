package com.lps.api.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    // @JsonIgnore
    // private List<Purchases> purchases;

    public Student(String name, String email, String password, String cpf, Long balance, String rg, Address address,
            Course course) {
        super(name, email, password, cpf, balance);
        this.rg = rg;
        this.address = address;
        this.course = course;
    }
}
