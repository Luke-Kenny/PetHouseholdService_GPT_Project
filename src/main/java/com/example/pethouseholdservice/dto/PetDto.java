package com.example.pethouseholdservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PetDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Animal type cannot be blank")
    private String animalType;

    @NotBlank(message = "Breed cannot be blank")
    private String breed;

    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private int age;

    @NotBlank(message = "Household Eircode cannot be blank")
    private String householdEircode;
}
