package com.lps.api.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lps.api.dtos.StudentRegisterDto;
import com.lps.api.models.Address;
import com.lps.api.models.Student;
import com.lps.api.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        when(studentService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(studentService, times(1)).findAll();
    }

    @Test
    public void testFindByCourse() throws Exception {
        String course = "Math";
        when(studentService.findByCourse(course)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/students/course/{course}", course))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(studentService, times(1)).findByCourse(course);
    }

    @Test
    public void testFindById() throws Exception {
        Long id = 1L;
        Student student = new Student(
                "John Doe", "john@example.com", "password123", "12345678901",
                100.0, "987654321", null, null
        );
        when(studentService.findById(id)).thenReturn(student);

        mockMvc.perform(get("/students/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(student)));

        verify(studentService, times(1)).findById(id);
    }

    @Test
    public void testSave() throws Exception {
        Student student = new Student(
                "Jane Doe", "jane@example.com", "securePass", "10987654321",
                200.0, "123456789", null, null
        );
        when(studentService.save(any(StudentRegisterDto.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(student)));

        verify(studentService, times(1)).save(any(StudentRegisterDto.class));
    }

    @Test
    public void testDeleteById() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/students/{id}", id))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteById(id);
    }
}