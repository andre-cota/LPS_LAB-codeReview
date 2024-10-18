package com.lps.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class    Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO  )
    private Long id;

    @Column(name = "street", nullable = false, unique = false)
    private String street;
    @Column(name = "number", nullable = false, unique = false)
    private Integer number;
    @Column(name = "complement", nullable = true, unique = false)
    private String complement;
    @Column(name = "neighborhood", nullable = false, unique = false)
    private String neighborhood;
    @Column(name = "city", nullable = false, unique = false)
    private String city;
    @Column(name = "state", nullable = false, unique = false)
    private String state;
    @Column(name = "zip_code", nullable = false, unique = false)
    private String zipCode;

    @OneToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "student_id", unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    private Student student;
}
