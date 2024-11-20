package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
