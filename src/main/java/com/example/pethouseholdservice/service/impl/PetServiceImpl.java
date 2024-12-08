package com.example.pethouseholdservice.service.impl;

import com.example.pethouseholdservice.dto.PetDto;
import com.example.pethouseholdservice.dto.PetStatisticsDTO;
import com.example.pethouseholdservice.entity.Household;
import com.example.pethouseholdservice.entity.Pet;
import com.example.pethouseholdservice.exception.ResourceNotFoundException;
import com.example.pethouseholdservice.repository.HouseholdRepository;
import com.example.pethouseholdservice.repository.PetRepository;
import com.example.pethouseholdservice.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet findPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + id));
    }

    @Override
    public Pet savePet(PetDto petDto) {
        Household household = householdRepository.findById(petDto.getHouseholdEircode())
                .orElseThrow(() -> new ResourceNotFoundException("Household not found with eircode: " + petDto.getHouseholdEircode()));
        Pet pet = new Pet();
        pet.setName(petDto.getName());
        pet.setAnimalType(petDto.getAnimalType());
        pet.setBreed(petDto.getBreed());
        pet.setAge(petDto.getAge());
        pet.setHousehold(household);
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePetName(Long id, String newName) {
        Pet pet = findPetById(id);
        pet.setName(newName);
        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Long id) {
        Pet pet = findPetById(id);
        petRepository.delete(pet);
    }

    @Override
    public PetStatisticsDTO getPetStatistics() {
        double averageAge = petRepository.findAverageAge();
        int oldestAge = petRepository.findOldestAge();
        long totalCount = petRepository.findTotalCount();

        return new PetStatisticsDTO(averageAge, oldestAge, totalCount);
    }
}
