package com.example.pethouseholdservice.repository;

import com.example.pethouseholdservice.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("SELECT AVG(p.age) FROM Pet p")
    double findAverageAge();

    @Query("SELECT MAX(p.age) FROM Pet p")
    int findOldestAge();

    @Query("SELECT COUNT(p) FROM Pet p")
    long findTotalCount();
}
