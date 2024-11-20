package com.lps.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    Student findByCpf(String cpf);

    List<Student> findByCourse_Name(String course);
}
