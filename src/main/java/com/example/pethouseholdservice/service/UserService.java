package com.example.pethouseholdservice.service;

import com.example.pethouseholdservice.dto.UserDto;
import com.example.pethouseholdservice.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(UserDto userDto);

    void toggleUserLock(String username);

    void deleteUserByUsername(String username);
}
