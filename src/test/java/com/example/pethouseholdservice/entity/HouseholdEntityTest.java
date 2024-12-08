package com.example.pethouseholdservice.entity;

import com.example.pethouseholdservice.repository.HouseholdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class HouseholdEntityTest {

    @Autowired
    private HouseholdRepository householdRepository;

    @BeforeEach
    public void setUp() {
        // Clear the repository before each test
        householdRepository.deleteAll();
    }

    @Test
    public void testGetHouseholds() {
        // Arrange
        Household household1 = new Household();
        household1.setEircode("D01A123");
        household1.setNumberOfOccupants(3);
        household1.setMaxNumberOfOccupants(5);
        household1.setOwnerOccupied(true);

        Household household2 = new Household();
        household2.setEircode("D02B456");
        household2.setNumberOfOccupants(2);
        household2.setMaxNumberOfOccupants(4);
        household2.setOwnerOccupied(false);

        householdRepository.save(household1);
        householdRepository.save(household2);

        // Act
        List<Household> households = householdRepository.findAll();

        // Assert
        assertNotNull(households, "Households should not be null");
        assertEquals(2, households.size(), "There should be two households in the repository");

        // Verify the data
        Household retrievedHousehold1 = households.get(0);
        Household retrievedHousehold2 = households.get(1);

        assertEquals("D01A123", retrievedHousehold1.getEircode(), "Eircode of household1 should match");
        assertEquals("D02B456", retrievedHousehold2.getEircode(), "Eircode of household2 should match");
    }
}
