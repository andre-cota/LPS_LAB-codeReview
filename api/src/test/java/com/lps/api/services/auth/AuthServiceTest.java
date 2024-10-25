package com.lps.api.services.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.lps.api.models.Student;
import com.lps.api.models.User;
import com.lps.api.repositories.UserRepository;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserIsStudent() {
        // Mock a Student user
        Student student = new Student();
        student.setEmail("student@example.com");
        student.setPassword("password");

        when(userRepository.findByEmail("student@example.com")).thenReturn(student);

        // Call the method to be tested
        UserDetails userDetails = authService.loadUserByUsername("student@example.com");

        // Verify results
        assertNotNull(userDetails);
        assertEquals("student@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("isStudent")));

        // Verify interactions with the repository
        verify(userRepository, times(1)).findByEmail("student@example.com");
    }

    @Test
    void testLoadUserByUsername_UserIsAgencia() {
        // Mock a regular User (not a Student)
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail("user@example.com")).thenReturn(user);

        // Call the method to be tested
        UserDetails userDetails = authService.loadUserByUsername("user@example.com");

        // Verify results
        assertNotNull(userDetails);
        assertEquals("user@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("isAgencia")));

        // Verify interactions with the repository
        verify(userRepository, times(1)).findByEmail("user@example.com");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Mock repository to return null for a non-existent user
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Verify that the exception is thrown
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            authService.loadUserByUsername("nonexistent@example.com");
        });

        assertEquals("Usuário não encontrado com o email: nonexistent@example.com", exception.getMessage());

        // Verify interactions with the repository
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }
}
