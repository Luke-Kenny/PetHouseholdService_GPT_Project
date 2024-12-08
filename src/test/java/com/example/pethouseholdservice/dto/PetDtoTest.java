package com.example.pethouseholdservice.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidPetDto() {
        // Arrange
        PetDto petDto = new PetDto();
        petDto.setName("Buddy");
        petDto.setAnimalType("Dog");
        petDto.setBreed("Golden Retriever");
        petDto.setAge(3);
        petDto.setHouseholdEircode("D01X2E4");

        // Act
        Set<ConstraintViolation<PetDto>> violations = validator.validate(petDto);

        // Assert
        assertTrue(violations.isEmpty(), "No violations should occur for valid PetDto");
    }

    @Test
    public void testInvalidPetDto() {
        // Arrange
        PetDto petDto = new PetDto();
        petDto.setName(""); // Invalid: blank name
        petDto.setAnimalType(""); // Invalid: blank animal type
        petDto.setBreed(""); // Invalid: blank breed
        petDto.setAge(-1); // Invalid: age less than 0
        petDto.setHouseholdEircode(""); // Invalid: blank eircode

        // Act
        Set<ConstraintViolation<PetDto>> violations = validator.validate(petDto);

        // Assert
        assertEquals(5, violations.size(), "There should be 5 violations");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Name cannot be blank")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Animal type cannot be blank")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Breed cannot be blank")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Age must be greater than or equal to 0")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Household Eircode cannot be blank")));
    }
}
