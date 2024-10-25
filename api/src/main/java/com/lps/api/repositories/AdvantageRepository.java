package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Advantage;

public interface AdvantageRepository extends JpaRepository<Advantage, Long> {
    
}
