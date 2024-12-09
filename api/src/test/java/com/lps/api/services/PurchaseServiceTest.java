package com.lps.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lps.api.models.Department;
import com.lps.api.models.Professor;
import com.lps.api.repositories.ProfessorRepository;


class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private PurchasesRepository purchaseRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private AdvantageService advantageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByStudentId() {
        // Arrange
        Long studentId = 1L;
        Purchases purchase1 = new Purchases();
        Purchases purchase2 = new Purchases();
        List<Purchases> expectedPurchases = Arrays.asList(purchase1, purchase2);
        when(purchaseRepository.findByStudentId(studentId)).thenReturn(expectedPurchases);

        // Act
        List<Purchases> actualPurchases = purchaseService.findByStudentId(studentId);

        // Assert
        assertEquals(expectedPurchases, actualPurchases);
    }

    @Test
    void testSave_Success() {
        // Arrange
        Long studentId = 1L;
        Long advantageId = 1L;
        int quantity = 2;
        double price = 50.0;
        PurchaseRequestDTO request = new PurchaseRequestDTO(studentId, advantageId, quantity, price);

        Student student = new Student();
        student.setBalance(200.0);
        Advantage advantage = new Advantage();
        Purchases expectedPurchase = new Purchases();
        expectedPurchase.setStudent(student);
        expectedPurchase.setAdvantage(advantage);
        expectedPurchase.setQuantity(quantity);
        expectedPurchase.setPrice(price);
        expectedPurchase.setDate(LocalDate.now());

        when(studentService.findById(studentId)).thenReturn(student);
        when(advantageService.findById(advantageId)).thenReturn(advantage);
        when(purchaseRepository.save(expectedPurchase)).thenReturn(expectedPurchase);

        // Act
        Purchases actualPurchase = purchaseService.save(request);

        // Assert
        assertNotNull(actualPurchase);
        assertEquals(expectedPurchase.getStudent(), actualPurchase.getStudent());
        assertEquals(expectedPurchase.getAdvantage(), actualPurchase.getAdvantage());
        assertEquals(expectedPurchase.getQuantity(), actualPurchase.getQuantity());
        assertEquals(expectedPurchase.getPrice(), actualPurchase.getPrice());
        assertEquals(expectedPurchase.getDate(), actualPurchase.getDate());
    }

    @Test
    void testSave_InsufficientBalance() {
        // Arrange
        Long studentId = 1L;
        Long advantageId = 1L;
        int quantity = 2;
        double price = 150.0;
        PurchaseRequestDTO request = new PurchaseRequestDTO(studentId, advantageId, quantity, price);

        Student student = new Student();
        student.setBalance(100.0);

        when(studentService.findById(studentId)).thenReturn(student);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            purchaseService.save(request);
        });

        assertEquals("Saldo insuficiente", exception.getMessage());
    }
}