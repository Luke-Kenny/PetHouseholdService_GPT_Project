package com.example.pethouseholdservice.entity;

import com.example.pethouseholdservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserEntityTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUpdateUserRole() {
        // Arrange
        User user = userRepository.findByUsername("user@example.com").orElseThrow();
        assertEquals("USER", user.getRole(), "Initial role should be USER");

        // Act
        user.setRole("ADMIN");
        userRepository.save(user);

        // Assert
        User updatedUser = userRepository.findById(String.valueOf(user.getId())).orElseThrow();
        assertEquals("ADMIN", updatedUser.getRole(), "Role should be updated to ADMIN");
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        User user = userRepository.findByUsername("admin@example.com").orElseThrow();

        // Act
        userRepository.delete(user);

        // Assert
        assertFalse(userRepository.findById(String.valueOf(user.getId())).isPresent(), "User should be deleted from the repository");
    }
}
