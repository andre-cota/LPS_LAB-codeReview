package com.lps.api.services;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.dtos.SendCoinsRequestDTO;
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

    public Professor updateProfessor(Long id, Professor professor) {
        Optional<Professor> existingProfessor = professorRepository.findById(id);
        if (existingProfessor.isPresent()) {
            Professor updatedProfessor = existingProfessor.get();
            updatedProfessor.setName(professor.getName());
            updatedProfessor.setDepartment(professor.getDepartment());
            updatedProfessor.setBalance(professor.getBalance());

            return professorRepository.save(updatedProfessor);
        } else {
            throw new RuntimeException("Professor not found with id " + id);
        }
    }

    public Professor sendCoins(SendCoinsRequestDTO sendCoinsRequestDTO) throws BadRequestException {
        Professor professor = this.findById(sendCoinsRequestDTO.professorId());
        Student student = this.studentService.findById(sendCoinsRequestDTO.studentId());

        Long quantity = sendCoinsRequestDTO.amount();
        if (quantity <= 0 || quantity > professor.getBalance()) {
            throw new BadRequestException("Numero de moedas inválido.");
        }

        student.setBalance(student.getBalance() + quantity);
        studentService.update(student);

        professor.setBalance(professor.getBalance() - quantity);
        this.professorRepository.save(professor);

        return professor;
    }

}
