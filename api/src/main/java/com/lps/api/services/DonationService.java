package com.lps.api.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.dtos.SendCoinsRequestDTO;
import com.lps.api.models.Donation;
import com.lps.api.models.Professor;
import com.lps.api.models.Student;
import com.lps.api.repositories.DonationRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;

    @Transactional
    public Donation donate(SendCoinsRequestDTO request) throws BadRequestException{
        Professor professor = this.professorService.findById(request.professorId());
        Student student = this.studentService.findById(request.studentId());

        Long quantity = request.amount();
        if (quantity <= 0 || quantity > professor.getBalance()) {
            throw new BadRequestException("Numero de moedas inválido.");
        }

        Donation donation = new Donation(quantity, professor, student);
        this.donationRepository.save(donation);

        professor.setBalance(professor.getBalance() - quantity);
        student.setBalance(student.getBalance() + quantity);

        this.professorService.save(professor);
        this.studentService.update(student);

        return donation;
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public List<Donation> findByProfessorId(Long id) {
        return donationRepository.findByProfessorId(id);
    }

    public List<Donation> findByStudentId(Long id) {
        return donationRepository.findByStudentId(id);
    }
}
