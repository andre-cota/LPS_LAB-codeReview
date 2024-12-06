package com.lps.api.services;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.dtos.PurchaseRequestDTO;
import com.lps.api.models.Advantage;
import com.lps.api.models.Purchases;
import com.lps.api.models.Student;
import com.lps.api.repositories.PurchasesRepository;
import com.lps.api.repositories.StudentRepository;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    private PurchasesRepository purchaseRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdvantageService advantageService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    public List<Purchases> findByStudentId(Long studentId) {
        return purchaseRepository.findByStudentId(studentId);
    }

    public Purchases save(PurchaseRequestDTO request){
        Purchases purchase = new Purchases();
        Student student = studentService.findById(request.studentId());
        Advantage advantage = advantageService.findById(request.advantageId());

        try {
            emailSenderService.studentBuyAdventage(student.getEmail(), advantage.getCompany().getEmail(), advantage.getName(), request.quantity());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        if(student.getBalance() < request.price()){
            throw new RuntimeException("Saldo insuficiente");
        }

        purchase.setStudent(student);
        purchase.setAdvantage(advantage);
        purchase.setQuantity(request.quantity());
        purchase.setPrice(request.price());

        LocalDate date = LocalDate.now();
        purchase.setDate(date);

        Purchases toReturn = this.purchaseRepository.save(purchase);

        student.setBalance(student.getBalance() - request.price());
        studentRepository.save(student);

        return toReturn;
    }

}
