package com.example.pethouseholdservice.service;

import com.example.pethouseholdservice.dto.PetDto;
import com.example.pethouseholdservice.dto.PetStatisticsDTO;
import com.example.pethouseholdservice.entity.Household;
import com.example.pethouseholdservice.entity.Pet;
import com.example.pethouseholdservice.exception.ResourceNotFoundException;
import com.example.pethouseholdservice.repository.HouseholdRepository;
import com.example.pethouseholdservice.repository.PetRepository;
import com.example.pethouseholdservice.service.impl.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPets() {
        // Arrange
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Buddy");

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setName("Max");

        when(petRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2));

        // Act
        List<Pet> pets = petService.getAllPets();

        // Assert
        assertNotNull(pets, "Pets list should not be null");
        assertEquals(2, pets.size(), "There should be 2 pets");
        assertEquals("Buddy", pets.get(0).getName(), "First pet's name should be Buddy");
        assertEquals("Max", pets.get(1).getName(), "Second pet's name should be Max");
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void testFindPetById_PetExists() {
        // Arrange
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Buddy");

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        // Act
        Pet foundPet = petService.findPetById(1L);

        // Assert
        assertNotNull(foundPet, "Pet should not be null");
        assertEquals("Buddy", foundPet.getName(), "Pet's name should be Buddy");
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    void testFindPetById_PetDoesNotExist() {
        // Arrange
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> petService.findPetById(1L), "Should throw ResourceNotFoundException");
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    void testSavePet() {
        // Arrange
        Household household = new Household();
        household.setEircode("EIR123");

        PetDto petDto = new PetDto();
        petDto.setName("Buddy");
        petDto.setAnimalType("Dog");
        petDto.setBreed("Golden Retriever");
        petDto.setAge(3);
        petDto.setHouseholdEircode("EIR123");

        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Buddy");
        pet.setHousehold(household);

        when(householdRepository.findById("EIR123")).thenReturn(Optional.of(household));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        // Act
        Pet savedPet = petService.savePet(petDto);

        // Assert
        assertNotNull(savedPet, "Saved pet should not be null");
        assertEquals("Buddy", savedPet.getName(), "Pet's name should be Buddy");
        verify(householdRepository, times(1)).findById("EIR123");
        verify(petRepository, times(1)).save(any(Pet.class));
    }
}
