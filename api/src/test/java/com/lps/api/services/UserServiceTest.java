package com.lps.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lps.api.dtos.ChangePasswordDTO;
import com.lps.api.exceptions.ChangePasswordException;
import com.lps.api.exceptions.ObjectNotFoundException;
import com.lps.api.models.PasswordResetToken;
import com.lps.api.models.User;
import com.lps.api.repositories.PasswordTokenRepository;
import com.lps.api.repositories.UserRepository;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordTokenRepository passwordTokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById_Success() {
        // Arrange
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        User result = userService.getById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetById_NotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ObjectNotFoundException.class, () -> userService.getById(userId));
        verify(userRepository).findById(userId);
    }

    @Test
    void testChangePassword_InvalidToken() {
        // Arrange
        String email = "test@example.com";
        String token = "invalidToken";
        String newPassword = "newPassword";

        User user = new User();
        user.setEmail(email);

        ChangePasswordDTO dto = new ChangePasswordDTO(email, token, newPassword, newPassword);

        when(userRepository.findByEmail(email)).thenReturn(user);
        when(passwordTokenRepository.findByUserAndToken(user, token)).thenReturn(null);

        // Act & Assert
        assertThrows(ChangePasswordException.class, () -> userService.changePassword(dto));
    }

    @Test
    void testCreatePasswordResetToken_Success() {
        // Arrange
        Long userId = 1L;
        String token = "newToken";
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(passwordTokenRepository.findByUser(user)).thenReturn(null);

        // Act
        userService.createPasswordResetTokenForUser(userId, token);

        // Assert
        verify(passwordTokenRepository).save(any(PasswordResetToken.class));
    }

    @Test
    void testDeleteUserById_Success() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User deletedUser = userService.deleteUserById(userId);

        // Assert
        assertNotNull(deletedUser);
        assertEquals(userId, deletedUser.getId());
        verify(userRepository).delete(user);
    }

    @Test
    void testFindByEmail_Success() {
        // Arrange
        String email = "test@example.com";
        User expectedUser = new User();
        expectedUser.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(expectedUser);

        // Act
        User result = userService.findByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userRepository).findByEmail(email);
    }

    // 2. Test createPasswordResetTokenForUser (else branch)
    @Test
    void testCreatePasswordResetTokenForUser_ExistingToken() {
        // Arrange
        Long userId = 1L;
        String token = "newToken";
        User user = new User();
        user.setId(userId);
        PasswordResetToken existingToken = new PasswordResetToken(null, "oldToken", user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(passwordTokenRepository.findByUser(user)).thenReturn(existingToken);

        // Act
        userService.createPasswordResetTokenForUser(userId, token);

        // Assert
        verify(passwordTokenRepository).save(existingToken);
        assertEquals(token, existingToken.getToken());
    }

    // 3. Test update
    @Test
    void testUpdate_Success() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setEmail("teste@gmail.com");
        user.setName("pedro");
        user.setPassword("pedro");
        User existingUser = new User();
        existingUser.setId(userId);
        user.setEmail("test@gm.co");
        user.setName("pedro lambert");
        user.setPassword("pedro lambert");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.update(user, userId);

        // Assert
        verify(userRepository).save(user);
        assertEquals(userId, user.getId());
    }

    // 4. Test changePassword when token == null
    @Test
    void testChangePassword_TokenNull() {
        // Arrange
        ChangePasswordDTO dto = new ChangePasswordDTO("test@example.com", null, "newPassword", "newPassword");

        // Act & Assert
        assertThrows(ChangePasswordException.class, () -> userService.changePassword(dto));
    }

    // 5. Test changePassword when user == null
    @Test
    void testChangePassword_UserNull() {
        // Arrange
        String email = "test@example.com";
        String token = "validToken";
        ChangePasswordDTO dto = new ChangePasswordDTO(email, token, "newPassword", "newPassword");

        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act & Assert
        assertThrows(ChangePasswordException.class, () -> userService.changePassword(dto));
    }

    // 6. Test changePassword when newPassword != confirmationNewPassword
    @Test
    void testChangePassword_PasswordMismatch() {
        // Arrange
        String email = "test@example.com";
        String token = "validToken";
        User user = new User();
        user.setEmail(email);

        ChangePasswordDTO dto = new ChangePasswordDTO(email, token, "newPassword", "differentPassword");

        when(userRepository.findByEmail(email)).thenReturn(user);
        when(passwordTokenRepository.findByUserAndToken(user, token)).thenReturn(new PasswordResetToken(null, token, user));

        // Act & Assert
        assertThrows(ChangePasswordException.class, () -> userService.changePassword(dto));
    }

    // 7. Test getBcrypt
    @Test
    void testGetBcrypt() {
        // Act
        PasswordEncoder encoder = userService.getBcrypt();

        // Assert
        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }
}