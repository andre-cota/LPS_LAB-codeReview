package com.lps.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.api.dtos.StudentRegisterDto;
import com.lps.api.dtos.StudentResponseDTO;
import com.lps.api.models.Student;
import com.lps.api.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> findAll() {
        List<Student> savedStudent = studentService.findAll();
        List<StudentResponseDTO> response = savedStudent.stream().map(student -> new StudentResponseDTO(
            student.getName(), 
            student.getEmail(),
            student.getCpf(),
            student.getBalance(),
            student.getRg(),
            student.getCourse(),
            student.getAddress())).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<List<StudentResponseDTO>> findByCourse(@PathVariable String course) {
        List<Student> savedStudent = studentService.findByCourse(course);
        List<StudentResponseDTO> response = savedStudent.stream().map(student -> new StudentResponseDTO(
            student.getName(), 
            student.getEmail(),
            student.getCpf(),
            student.getBalance(),
            student.getRg(),
            student.getCourse(),
            student.getAddress())).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> findById(@PathVariable Long id) {
        Student savedStudent = studentService.findById(id);
        StudentResponseDTO response = new StudentResponseDTO(
            savedStudent.getName(), 
            savedStudent.getEmail(),
            savedStudent.getCpf(),
            savedStudent.getBalance(),
            savedStudent.getRg(),
            savedStudent.getCourse(),
            savedStudent.getAddress());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> save(@RequestBody StudentRegisterDto student) {
        Student savedStudent = studentService.save(student);
        StudentResponseDTO response = new StudentResponseDTO(
            savedStudent.getName(), 
            savedStudent.getEmail(),
            savedStudent.getCpf(),
            savedStudent.getBalance(),
            savedStudent.getRg(),
            savedStudent.getCourse(),
            savedStudent.getAddress());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
