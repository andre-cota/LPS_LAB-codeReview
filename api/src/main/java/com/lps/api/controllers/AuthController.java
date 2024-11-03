package com.lps.api.controllers;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lps.api.dtos.ChangePasswordDTO;
import com.lps.api.dtos.ForgetPasswordDTO;
import com.lps.api.dtos.auth.LoginRequest;
import com.lps.api.dtos.auth.LoginResponse;
import com.lps.api.models.NaturalPerson;
import com.lps.api.models.Student;
import com.lps.api.models.User;
import com.lps.api.security.JwtTokenProvider;
import com.lps.api.services.EmailSenderService;
import com.lps.api.services.UserService;

import jakarta.mail.MessagingException;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.email());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()));
        String userType = (user instanceof NaturalPerson) ? "isStudentOrTeacher" : "isEnterprise";
        if(!userType.equals("isEnterprise")) {
            userType = (user instanceof Student) ? "isStudent" : "isTeacher";
        }
        String jwt = tokenProvider.generateToken(authentication);
        Long id = user.getId();
        return ResponseEntity.ok(new LoginResponse(jwt, userType, id));

    }

    @PostMapping("/changePassword")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        userService.changePassword(changePasswordDTO);
        return ResponseEntity.status(200).body("Password changed");

    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> sendToken(@RequestBody ForgetPasswordDTO forgetPasswordDTO)
            throws UnsupportedEncodingException, NoSuchElementException, MessagingException {

        User user = userService.findByEmail(forgetPasswordDTO.email());
        String token = UUID.randomUUID().toString().substring(0, 5);
        userService.createPasswordResetTokenForUser(user.getId(), token);
        emailSenderService.sendRecoveryPasswordMail(user.getEmail(), token);
        return ResponseEntity.status(200).body("Email enviado para o usuário " +
                user.getEmail());

    }

}
