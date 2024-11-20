package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
