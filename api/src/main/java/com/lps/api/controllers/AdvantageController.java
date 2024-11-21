package com.lps.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.api.dtos.AdvantageCreateAndUpdateRequest;
import com.lps.api.models.Advantage;
import com.lps.api.services.AdvantageService;


@RestController
@RequestMapping("/advantages")
public class AdvantageController {
    @Autowired
    private AdvantageService advantageService;

    @GetMapping("/{companyId}")
    public ResponseEntity<List<Advantage>> getAdvantagesByCompanyId(@PathVariable Long companyId) {
        List<Advantage> advantages = advantageService.findByCompanyId(companyId);
        return ResponseEntity.ok().body(advantages);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvantage(@PathVariable Long id) {
        advantageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Advantage> createAdvantage(@RequestBody AdvantageCreateAndUpdateRequest advantage) {
        Advantage newAdvantage = advantageService.save(advantage);
        return ResponseEntity.ok().body(newAdvantage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Advantage> updateAdvantage(@PathVariable Long id, @RequestBody AdvantageCreateAndUpdateRequest advantageDetails) {
        Advantage updatedAdvantage = advantageService.updateAdvantage(id, advantageDetails);
        if (updatedAdvantage != null) {
            return ResponseEntity.ok(updatedAdvantage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Advantage>> getAllAdvantages() {
        List<Advantage> advantages = advantageService.findAll();
        return ResponseEntity.ok().body(advantages);
    }
}
