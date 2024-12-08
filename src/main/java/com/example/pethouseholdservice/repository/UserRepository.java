package com.example.pethouseholdservice.repository;

import com.example.pethouseholdservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // Find a user by username
    Optional<User> findByUsername(String username);

    // Find users by role
    List<User> findByRole(String role);

    // Find users based on lock status
    List<User> findByLocked(boolean locked);
}
