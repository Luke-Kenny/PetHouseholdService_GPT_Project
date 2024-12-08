package com.example.pethouseholdservice.controller;


import com.example.pethouseholdservice.entity.Household;
import com.example.pethouseholdservice.entity.Pet;
import com.example.pethouseholdservice.service.HouseholdService;
import com.example.pethouseholdservice.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLController {

    private final HouseholdService householdService;
    private final PetService petService;


    @QueryMapping
    @Secured("ROLE_USER") // Accessible to USER and ADMIN roles
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    @Secured("ROLE_USER") // Accessible to USER and ADMIN roles
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @MutationMapping
    @Secured("ROLE_ADMIN")
    public boolean deletePet(@Argument Long id) {
        petService.deletePetById(id);
        return true;
    }
}
