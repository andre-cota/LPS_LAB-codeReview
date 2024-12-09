package com.lps.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lps.api.dtos.ChangePasswordDTO;
import com.lps.api.dtos.ForgetPasswordDTO;
import com.lps.api.models.User;
import com.lps.api.repositories.UserRepository;

import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void forgetPassword(ForgetPasswordDTO forgetPasswordDTO) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(forgetPasswordDTO.email());
        if (user != null) {

            String resetToken = generateResetToken(user);

            String resetLink = "https://example.com/reset-password?token=" + resetToken;
            String message = "Clique no link para redefinir sua senha: " + resetLink;
            emailSenderService.sendEmail(user.getEmail(), "Redefinição de Senha", message);
        }
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findByEmail(changePasswordDTO.email());
        if (user != null && validateResetToken(user, changePasswordDTO.token())) {
            if (changePasswordDTO.isPasswordConfirmed()) {
                user.setPassword(passwordEncoder.encode(changePasswordDTO.novaSenha()));
                userRepository.save(user);
            } else {
                throw new IllegalArgumentException("A nova senha e a confirmação da nova senha não coincidem.");
            }
        } else {
            throw new IllegalArgumentException("Token inválido ou usuário não encontrado.");
        }
    }
}