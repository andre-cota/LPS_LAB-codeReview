package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Purchases;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
    
}
