package com.lps.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.models.Department;
import com.lps.api.repositories.DepartmentRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).get();
    }
}
