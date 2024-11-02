package com.lps.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lps.api.models.Department;
import com.lps.api.models.Professor;
import com.lps.api.repositories.ProfessorRepository;

class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Professor> professors = Arrays.asList(new Professor(), new Professor());
        when(professorRepository.findAll()).thenReturn(professors);

        List<Professor> result = professorService.findAll();

        assertEquals(2, result.size());
        verify(professorRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Professor professor = new Professor();
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

        Professor result = professorService.findById(1L);

        assertTrue(result != null);
        assertEquals(professor, result);
        verify(professorRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        Professor professor = new Professor();
        when(professorRepository.save(any(Professor.class))).thenReturn(professor);

        Professor result = professorService.save(professor);

        assertEquals(professor, result);
        verify(professorRepository, times(1)).save(professor);
    }

    @Test
    void testDeleteById() {
        doNothing().when(professorRepository).deleteById(1L);

        professorService.deleteById(1L);

        verify(professorRepository, times(1)).deleteById(1L);
    }
}
