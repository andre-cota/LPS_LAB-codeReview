package com.lps.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private Integer number;
    @Column
    private String complement;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
}
