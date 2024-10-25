package com.lps.api.dtos;

import com.lps.api.models.Address;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentRegisterDtoTest {

    @Test
    void testStudentRegisterDtoCreation() {
        String name = "John Doe";
        String email = "john@example.com";
        String password = "securePassword";
        String cpf = "12345678900";
        Double balance = 1000.0;
        String rg = "MG1234567";
        Long courseId = 1L;
        Address address = new Address(courseId, "Street", null, "City", "State", "Zip", rg, rg, null);

        StudentRegisterDto studentRegisterDto = new StudentRegisterDto(
                name, email, password, cpf, balance, rg, courseId, address);

        assertEquals(name, studentRegisterDto.name());
        assertEquals(email, studentRegisterDto.email());
        assertEquals(password, studentRegisterDto.password());
        assertEquals(cpf, studentRegisterDto.cpf());
        assertEquals(balance, studentRegisterDto.balance());
        assertEquals(rg, studentRegisterDto.rg());
        assertEquals(courseId, studentRegisterDto.courseId());
        assertEquals(address, studentRegisterDto.address());
    }

    @Test
    void testStudentRegisterDtoEquality() {
        Address address = new Address(null, "Street", null, "City", "State", "Zip", null, null, null);
        StudentRegisterDto dto1 = new StudentRegisterDto(
                "John Doe", "john@example.com", "securePassword", 
                "12345678900", 1000.0, "MG1234567", 1L, address);
        StudentRegisterDto dto2 = new StudentRegisterDto(
                "John Doe", "john@example.com", "securePassword", 
                "12345678900", 1000.0, "MG1234567", 1L, address);

        assertEquals(dto1, dto2);
    }

    @Test
    void testStudentRegisterDtoHashCode() {
        Address address = new Address(null, "Street", null, "City", "State", "Zip", null, null, null);
        StudentRegisterDto dto1 = new StudentRegisterDto(
                "John Doe", "john@example.com", "securePassword", 
                "12345678900", 1000.0, "MG1234567", 1L, address);
        StudentRegisterDto dto2 = new StudentRegisterDto(
                "John Doe", "john@example.com", "securePassword", 
                "12345678900", 1000.0, "MG1234567", 1L, address);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
