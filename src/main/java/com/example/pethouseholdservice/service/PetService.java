package com.example.pethouseholdservice.service;

import com.example.pethouseholdservice.dto.PetDto;
import com.example.pethouseholdservice.dto.PetStatisticsDTO;
import com.example.pethouseholdservice.entity.Pet;

import java.util.List;

public interface PetService {

    List<Pet> getAllPets();

    Pet findPetById(Long id);

    Pet savePet(PetDto petDto);

    Pet updatePetName(Long id, String newName);

    void deletePetById(Long id);

    PetStatisticsDTO getPetStatistics();
}
