package com.lps.api.controllers;

import com.lps.api.controllers.ProfessorController;
import com.lps.api.models.Professor;
import com.lps.api.services.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProfessorControllerTest {

    @InjectMocks
    private ProfessorController professorController;

    @Mock
    private ProfessorService professorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProfessors() {
        List<Professor> professors = Arrays.asList(new Professor(), new Professor());
        when(professorService.findAll()).thenReturn(professors);

        ResponseEntity<List<Professor>> response = professorController.getAllProfessors();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetProfessorById_Found() {
        Professor professor = new Professor();
        when(professorService.findById(1L)).thenReturn(Optional.of(professor));

        ResponseEntity<Professor> response = professorController.getProfessorById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(professor, response.getBody());
    }

    @Test
    public void testGetProfessorById_NotFound() {
        when(professorService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Professor> response = professorController.getProfessorById(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreateProfessor() {
        Professor professor = new Professor();
        when(professorService.save(any(Professor.class))).thenReturn(professor);

        ResponseEntity<Professor> response = professorController.createProfessor(professor);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(professor, response.getBody());
    }

    @Test
    public void testUpdateProfessor() {
        Professor professor = new Professor();
        when(professorService.updateProfessor(eq(1L), any(Professor.class))).thenReturn(professor);

        ResponseEntity<Professor> response = professorController.updateProfessor(1L, professor);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(professor, response.getBody());
    }

    @Test
    public void testDeleteProfessor() {
        doNothing().when(professorService).deleteById(1L);

        ResponseEntity<Void> response = professorController.deleteProfessor(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(professorService, times(1)).deleteById(1L);
    }
}