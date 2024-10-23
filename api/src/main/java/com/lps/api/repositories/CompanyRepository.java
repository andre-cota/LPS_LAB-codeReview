package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
