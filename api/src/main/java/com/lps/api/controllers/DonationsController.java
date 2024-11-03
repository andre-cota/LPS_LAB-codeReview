package com.lps.api.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.api.dtos.SendCoinsRequestDTO;
import com.lps.api.models.Donation;
import com.lps.api.models.Professor;
import com.lps.api.services.DonationService;

import java.util.List;


@RestController
@RequestMapping("/donations")
public class DonationsController {
    
    @Autowired
    private DonationService donationsService;

    @PostMapping
    public ResponseEntity<Donation> donate(@RequestBody SendCoinsRequestDTO requestDTO) throws BadRequestException {
        Donation donation = donationsService.donate(requestDTO);
        return ResponseEntity.ok().body(donation);
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<List<Donation>> getProfessorDonations(@PathVariable Long id) {
        List<Donation> donations = donationsService.findByProfessorId(id);
        return ResponseEntity.ok().body(donations);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Donation>> getStudentDonations(@PathVariable Long id) {
        List<Donation> donations = donationsService.findByStudentId(id);
        return ResponseEntity.ok().body(donations);
    }   
}
