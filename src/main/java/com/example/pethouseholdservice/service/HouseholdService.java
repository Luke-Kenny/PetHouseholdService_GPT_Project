package com.example.pethouseholdservice.service;

import com.example.pethouseholdservice.dto.HouseholdDto;
import com.example.pethouseholdservice.entity.Household;

import java.util.List;

public interface HouseholdService {

    Household findHouseholdByEircode(String eircode);

    Household findHouseholdWithPets(String eircode);

    List<Household> findHouseholdsWithNoPets();

    List<Household> getAllHouseholds();

    Household saveHousehold(HouseholdDto householdDto);

    void deleteHouseholdByEircode(String eircode);
}
