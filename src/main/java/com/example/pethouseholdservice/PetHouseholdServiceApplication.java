package com.example.pethouseholdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.pethouseholdservice")
public class PetHouseholdServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetHouseholdServiceApplication.class, args);
    }
}
