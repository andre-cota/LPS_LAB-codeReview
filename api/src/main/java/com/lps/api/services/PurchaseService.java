package com.lps.api.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.dtos.PurchaseRequestDTO;
import com.lps.api.models.Advantage;
import com.lps.api.models.Purchases;
import com.lps.api.models.Student;
import com.lps.api.repositories.PurchasesRepository;

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

    public List<Purchases> findByStudentId(Long studentId) {
        return purchaseRepository.findByStudentId(studentId);
    }

    public Purchases save(PurchaseRequestDTO request){
        Purchases purchase = new Purchases();
        Student student = studentService.findById(request.studentId());
        Advantage advantage = advantageService.findById(request.advantageId());
    
        purchase.setStudent(student);
        purchase.setAdvantage(advantage);
        purchase.setQuantity(request.quantity());
        purchase.setPrice(request.price());

        LocalDate date = LocalDate.now();
        purchase.setDate(date);

        Purchases toReturn = this.purchaseRepository.save(purchase);
        return toReturn;
    }

}
