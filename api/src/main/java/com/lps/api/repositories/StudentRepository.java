package com.lps.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    Student findByCpf(String cpf);

    List<Student> findByCourse(String course);
}
