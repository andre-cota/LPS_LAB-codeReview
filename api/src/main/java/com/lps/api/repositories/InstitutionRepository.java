package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
