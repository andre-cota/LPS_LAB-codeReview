package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
