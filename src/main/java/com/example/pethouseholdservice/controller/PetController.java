package com.example.pethouseholdservice.controller;

import com.example.pethouseholdservice.dto.PetDto;
import com.example.pethouseholdservice.entity.Pet;
import com.example.pethouseholdservice.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findPetById(id));
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDto petDto) {
        return ResponseEntity.ok(petService.savePet(petDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> updatePetName(@PathVariable Long id, @RequestParam String newName) {
        return ResponseEntity.ok(petService.updatePetName(id, newName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }
}
