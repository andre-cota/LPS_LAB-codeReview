package com.lps.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lps.api.models.Course;
import com.lps.api.repositories.CourseRepository;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Mock data
        Course course1 = new Course(1L, "Math", null, null);
        Course course2 = new Course(2L, "Science", null, null);

        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        // Call the method to be tested
        List<Course> result = courseService.findAll();

        // Verify results
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Math", result.get(0).getName());
        assertEquals("Science", result.get(1).getName());

        // Verify interactions with the repository
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Mock data
        Course course = new Course(1L, "Math", null, null);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // Call the method to be tested
        Course result = courseService.findById(1L);

        // Verify results
        assertNotNull(result);
        assertEquals("Math", result.getName());
        assertEquals(1L, result.getId());

        // Verify interactions with the repository
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        // Mock the repository to return an empty Optional
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        // Expect an exception
        assertThrows(RuntimeException.class, () -> {
            courseService.findById(1L);
        });

        // Verify interactions with the repository
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByName() {
        // Mock data
        Course course = new Course(1L, "Math", null, null);
        when(courseRepository.findByName("Math")).thenReturn(course);

        // Call the method to be tested
        Course result = courseService.findByName("Math");

        // Verify results
        assertNotNull(result);
        assertEquals("Math", result.getName());
        assertEquals(1L, result.getId());

        // Verify interactions with the repository
        verify(courseRepository, times(1)).findByName("Math");
    }
}
