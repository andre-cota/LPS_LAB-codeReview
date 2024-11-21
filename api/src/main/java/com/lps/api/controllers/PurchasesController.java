package com.lps.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lps.api.dtos.PurchaseRequestDTO;
import com.lps.api.dtos.PurchaseResponseDTO;
import com.lps.api.models.Purchases;
import com.lps.api.services.PurchaseService;

@RequestMapping("/purchases")
@RestController
public class PurchasesController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<PurchaseResponseDTO>> findByStudentId(@PathVariable Long studentId) {
        List<Purchases> purchases = purchaseService.findByStudentId(studentId);
        List<PurchaseResponseDTO> response = purchases.stream().map(purchase -> new PurchaseResponseDTO(
            purchase.getId(),
            purchase.getDate(),
            purchase.getPrice() * purchase.getQuantity(),
            purchase.getAdvantage().getName()
            )).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Purchases> createPurchase(@RequestBody PurchaseRequestDTO purchase) {
        Purchases newPurchase = purchaseService.save(purchase);
        return ResponseEntity.ok().body(newPurchase);
    }
}
