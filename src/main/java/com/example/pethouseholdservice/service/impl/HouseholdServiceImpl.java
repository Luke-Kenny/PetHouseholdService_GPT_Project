package com.example.pethouseholdservice.service.impl;

import com.example.pethouseholdservice.dto.HouseholdDto;
import com.example.pethouseholdservice.entity.Household;
import com.example.pethouseholdservice.exception.ResourceNotFoundException;
import com.example.pethouseholdservice.repository.HouseholdRepository;
import com.example.pethouseholdservice.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public Household findHouseholdByEircode(String eircode) {
        return householdRepository.findById(eircode)
                .orElseThrow(() -> new ResourceNotFoundException("Household not found with eircode: " + eircode));
    }

    @Override
    public Household findHouseholdWithPets(String eircode) {
        Household household = findHouseholdByEircode(eircode);
        if (household.getPets().isEmpty()) {
            throw new ResourceNotFoundException("Household has no pets with eircode: " + eircode);
        }
        return household;
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsByPetsIsEmpty();
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household saveHousehold(HouseholdDto householdDto) {
        Household household = new Household();
        household.setEircode(householdDto.getEircode());
        household.setNumberOfOccupants(householdDto.getNumberOfOccupants());
        household.setMaxNumberOfOccupants(householdDto.getMaxNumberOfOccupants());
        household.setOwnerOccupied(householdDto.isOwnerOccupied());
        return householdRepository.save(household);
    }

    @Override
    public void deleteHouseholdByEircode(String eircode) {
        Household household = findHouseholdByEircode(eircode);
        householdRepository.delete(household);
    }
}
