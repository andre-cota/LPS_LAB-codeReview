package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
