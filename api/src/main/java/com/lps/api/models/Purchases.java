package com.lps.api.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Double id;

    @Column(name = "name", nullable = false, unique = false)
    private Integer value;

    @Column(name = "description", nullable = false, unique = false)
    private Integer quantity;

    @Column(name = "date", nullable = false, unique = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "advantage_id", nullable = false)
    private Advantage advantage;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
