package com.example.pethouseholdservice.service;

import com.example.pethouseholdservice.entity.User;
import com.example.pethouseholdservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // This should be a hashed password
                .roles(user.getRole()) // Spring Security expects roles like "ROLE_ADMIN", "ROLE_USER"
                .disabled(!user.isEnabled())
                .accountLocked(user.isLocked())
                .build();
    }
}
