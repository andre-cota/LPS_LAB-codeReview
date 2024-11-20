package com.lps.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Advantage;

@Repository
public interface AdvantageRepository extends JpaRepository<Advantage, Long> {
    List<Advantage> findByCompanyId(Long companyId);
}
