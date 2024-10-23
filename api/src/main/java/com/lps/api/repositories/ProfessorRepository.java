package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
