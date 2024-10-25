package com.lps.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lps.api.dtos.StudentRegisterDto;
import com.lps.api.mappers.StudentMapper;
import com.lps.api.models.Address;
import com.lps.api.models.Course;
import com.lps.api.models.Student;
import com.lps.api.repositories.StudentRepository;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private AddressService addressService;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.findAll();

        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testFindByCourse() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findByCourse_Name("Math")).thenReturn(students);

        List<Student> result = studentService.findByCourse("Math");

        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findByCourse_Name("Math");
    }

    @Test
    void testFindById() {
        Student student = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.findById(1L);

        assertEquals(student, result);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByEmail() {
        Student student = new Student();
        when(studentRepository.findByEmail("test@example.com")).thenReturn(student);

        Student result = studentService.findByEmail("test@example.com");

        assertEquals(student, result);
        verify(studentRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testFindByCpf() {
        Student student = new Student();
        when(studentRepository.findByCpf("12345678900")).thenReturn(student);

        Student result = studentService.findByCpf("12345678900");

        assertEquals(student, result);
        verify(studentRepository, times(1)).findByCpf("12345678900");
    }

    @Test
    void testSave() {
        // Mock the StudentRegisterDto to return valid data
        StudentRegisterDto studentDto = mock(StudentRegisterDto.class);
        when(studentDto.courseId()).thenReturn(1L); // Mock course ID
        when(studentDto.name()).thenReturn("John Doe"); // Mock name
        when(studentDto.cpf()).thenReturn("12345678900"); // Mock CPF

        // Mock Course, Address, and Student objects
        Course course = new Course();
        Address address = new Address();
        Student student = new Student();
        student.setName("John Doe");
        student.setCpf("12345678900");
        student.setAddress(address); // Set the address to the student

        // Mock service and repository calls
        when(courseService.findById(anyLong())).thenReturn(course);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(addressService.save(any(Address.class))).thenReturn(address);

        // Call the service method to test
        Student result = studentService.save(studentDto);

        // Assertions
        assertEquals(student, result); // Assert the returned student matches the saved student

        // Verify repository and service interactions
        verify(studentRepository, times(2)).save(any(Student.class)); // Save is called twice
        verify(addressService, times(1)).save(any(Address.class)); // Address save is called once
    }

    @Test
    void testSaveThrowsExceptionWhenInvalidData() {
        StudentRegisterDto studentDto = mock(StudentRegisterDto.class);
        Course course = new Course();
        Student student = new Student();
        student.setName("");
        student.setCpf("");

        when(courseService.findById(anyLong())).thenReturn(course);
        when(studentDto.courseId()).thenReturn(1L);

        assertThrows(IllegalArgumentException.class, () -> {
            studentService.save(studentDto);
        });
    }

    @Test
    void testDeleteById() {
        doNothing().when(addressService).deleteByUserId(1L);
        doNothing().when(studentRepository).deleteById(1L);

        studentService.deleteById(1L);

        verify(addressService, times(1)).deleteByUserId(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete() {
        Student student = new Student();
        doNothing().when(studentRepository).delete(student);

        studentService.delete(student);

        verify(studentRepository, times(1)).delete(student);
    }
}
