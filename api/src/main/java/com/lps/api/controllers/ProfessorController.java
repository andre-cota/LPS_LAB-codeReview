package com.lps.api.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lps.api.dtos.SendCoinsRequestDTO;
import com.lps.api.dtos.UpdateProfessorDTO;
import com.lps.api.models.Professor;
import com.lps.api.services.ProfessorService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        List<Professor> professors = professorService.findAll();
        return ResponseEntity.ok(professors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        Professor professor = professorService.findById(id);
        return ResponseEntity.ok().body(professor);
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        return ResponseEntity.ok().body(professorService.save(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody UpdateProfessorDTO request) {
        Professor professor = professorService.updateProfessor(id, request);
        return ResponseEntity.ok().body(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}