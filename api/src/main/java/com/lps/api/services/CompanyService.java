package com.lps.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lps.api.models.Company;
import com.lps.api.repositories.CompanyRepository;

public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public Company save(Company Company) {
        return companyRepository.save(Company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public Company updateCompany(Long id, Company company) {
        Optional<Company> existingCompany = companyRepository.findById(id);
        if (existingCompany.isPresent()) {
            Company updatedCompany = existingCompany.get();
            updatedCompany.setName(company.getName());

            return companyRepository.save(updatedCompany);
        } else {
            throw new RuntimeException("Professor not found with id " + id);
        }
    }

}
