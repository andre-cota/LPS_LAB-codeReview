package com.lps.api.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.dtos.SendCoinsRequestDTO;
import com.lps.api.dtos.UpdateProfessorDTO;
import com.lps.api.models.Department;
import com.lps.api.models.Professor;
import com.lps.api.models.Student;
import com.lps.api.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id).get();
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }

    public Professor updateProfessor(Long id, UpdateProfessorDTO professor) {
        Professor existingProfessor = this.findById(id);
        Department department = this.departmentService.findById(professor.departmentId());

        existingProfessor.setName(professor.name());
        existingProfessor.setDepartment(department);
        existingProfessor.setBalance(professor.balance());
        existingProfessor.setEmail(professor.email());

        return professorRepository.save(existingProfessor);
    }
}
