package com.lps.api.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    private Department department;
    private Professor professor;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("Computer Science");

        professor = new Professor("John Doe", "john@example.com", "securePassword", "12345678900", 5000L);
        professor.setDepartment(department);
    }

    @Test
    void testProfessorCreation() {
        assertEquals("John Doe", professor.getName());
        assertEquals("john@example.com", professor.getEmail());
        assertEquals("securePassword", professor.getPassword());
        assertEquals("12345678900", professor.getCpf());
        assertEquals(5000L, professor.getBalance());
        assertEquals(department, professor.getDepartment());
    }

    @Test
    void testDepartmentAssociation() {
        assertNotNull(professor.getDepartment());
        assertEquals("Computer Science", professor.getDepartment().getName());
    }

    @Test
    void testInheritance() {
        assertTrue(professor instanceof NaturalPerson);
    }
}