package com.lps.api.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ForgetPasswordDTOTest {

    @Test
    void testForgetPasswordDTOCreation() {
        String email = "test@example.com";

        ForgetPasswordDTO forgetPasswordDTO = new ForgetPasswordDTO(email);

        assertEquals(email, forgetPasswordDTO.email());
    }

    @Test
    void testForgetPasswordDTOEquality() {
        ForgetPasswordDTO dto1 = new ForgetPasswordDTO("test@example.com");
        ForgetPasswordDTO dto2 = new ForgetPasswordDTO("test@example.com");

        assertEquals(dto1, dto2);
    }

    @Test
    void testForgetPasswordDTOHashCode() {
        ForgetPasswordDTO dto1 = new ForgetPasswordDTO("test@example.com");
        ForgetPasswordDTO dto2 = new ForgetPasswordDTO("test@example.com");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}