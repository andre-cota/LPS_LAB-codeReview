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

        Optional<Professor> result = professorService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(professor, result.get());
        verify(professorRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(professorRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Professor> result = professorService.findById(1L);

        assertFalse(result.isPresent());
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

    @Test
    void testUpdateProfessor() {
        Department department = new Department();
        department.setName("Department Name");
        Professor existingProfessor = new Professor();
        existingProfessor.setName("Original Name");
        Professor newProfessor = new Professor();
        newProfessor.setName("Updated Name");
        newProfessor.setDepartment(department);
        newProfessor.setBalance(5000.0);

        when(professorRepository.findById(1L)).thenReturn(Optional.of(existingProfessor));
        when(professorRepository.save(any(Professor.class))).thenReturn(existingProfessor);

        Professor result = professorService.updateProfessor(1L, newProfessor);

        assertEquals("Updated Name", result.getName());
        assertEquals("Department Name", result.getDepartment().getName());
        assertEquals(5000.0, result.getBalance());
        verify(professorRepository, times(1)).findById(1L);
        verify(professorRepository, times(1)).save(existingProfessor);
    }

    @Test
    void testUpdateProfessorNotFound() {
        Professor newProfessor = new Professor();
        when(professorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            professorService.updateProfessor(1L, newProfessor);
        });

        assertEquals("Professor not found with id 1", exception.getMessage());
        verify(professorRepository, times(1)).findById(1L);
        verify(professorRepository, never()).save(any(Professor.class));
    }
}
