package com.lps.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lps.api.models.Company;
import com.lps.api.services.CompanyService;
import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.findById(id).get();
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        Company updatedCompany = companyService.updateCompany(id, companyDetails);
        if (updatedCompany != null) {
            return ResponseEntity.ok(updatedCompany);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
