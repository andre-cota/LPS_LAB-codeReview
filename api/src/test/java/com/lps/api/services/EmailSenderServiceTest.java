package com.lps.api.services;

import static org.mockito.Mockito.*;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

class EmailSenderServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private Environment environment;

    @Mock
    private TemplateEngine htmlTemplateEngine;

    @InjectMocks
    private EmailSenderService emailSenderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendRecoveryPasswordMail() throws MessagingException, UnsupportedEncodingException {
        // Mock necessary values
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(environment.getProperty("spring.email.properties.email.smtp.from")).thenReturn("test@example.com");
        when(htmlTemplateEngine.process(eq("RecoveryPassword"), any(Context.class))).thenReturn("<html>Test Email Content</html>");

        // Call the method to be tested
        emailSenderService.sendRecoveryPasswordMail("user@example.com", "token123");

        // Verify interactions
        verify(mailSender, times(1)).createMimeMessage();
        verify(mailSender, times(1)).send(mimeMessage);
        verify(htmlTemplateEngine, times(1)).process(eq("RecoveryPassword"), any(Context.class));
        verify(environment, times(1)).getProperty("spring.email.properties.email.smtp.from");
    }

    @Test
    void testSendNewUser() throws MessagingException, UnsupportedEncodingException {
        // Mock necessary values
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(environment.getProperty("spring.mail.properties.mail.smtp.from")).thenReturn("test@example.com");
        when(htmlTemplateEngine.process(eq("NewUser"), any(Context.class))).thenReturn("<html>Welcome Email Content</html>");

        // Call the method to be tested
        emailSenderService.sendNewUser("user@example.com", "password123");

        // Verify interactions
        verify(mailSender, times(1)).createMimeMessage();
        verify(mailSender, times(1)).send(mimeMessage);
        verify(htmlTemplateEngine, times(1)).process(eq("NewUser"), any(Context.class));
        verify(environment, times(1)).getProperty("spring.mail.properties.mail.smtp.from");
    }
}
