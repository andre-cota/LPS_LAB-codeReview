package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Purchases;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
    
}
