package com.lps.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Purchases;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
    List<Purchases> findByStudentId(Long companyId);
}
