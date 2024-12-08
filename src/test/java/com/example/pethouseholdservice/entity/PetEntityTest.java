package com.example.pethouseholdservice.entity;

import com.example.pethouseholdservice.repository.HouseholdRepository;
import com.example.pethouseholdservice.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PetEntityTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Test
    public void testCascadeDeleteWithHousehold() {
        // Arrange
        Household household = new Household();
        household.setEircode("D01X2E4");
        household.setNumberOfOccupants(3);
        household.setMaxNumberOfOccupants(5);
        household.setOwnerOccupied(true);

        household = householdRepository.save(household);

        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setAnimalType("Dog");
        pet.setBreed("Golden Retriever");
        pet.setAge(3);
        pet.setHousehold(household);

        pet = petRepository.save(pet);

        // Act
        householdRepository.delete(household);

        // Assert
        assertTrue(petRepository.findById(pet.getId()).isPresent(),
                "Pet should be deleted when the associated household is deleted");
    }
}
