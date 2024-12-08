package com.example.pethouseholdservice.repository;

import com.example.pethouseholdservice.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, String> {

    // Fetch household and pets eagerly
    @Query("SELECT h FROM Household h LEFT JOIN FETCH h.pets WHERE h.eircode = :eircode")
    Optional<Household> findByEircodeWithPets(String eircode);

    // Fetch households with no pets
    @Query("SELECT h FROM Household h WHERE NOT EXISTS (SELECT 1 FROM Pet p WHERE p.household = h)")
    List<Household> findHouseholdsWithNoPets();

    // Find households with no pets
    List<Household> findHouseholdsByPetsIsEmpty();

    // Find households by owner occupancy status
    List<Household> findByOwnerOccupied(boolean ownerOccupied);
}
