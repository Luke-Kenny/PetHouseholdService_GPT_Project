package com.example.pethouseholdservice.dto;

import lombok.Data;

@Data
public class HouseholdDto {
    private String eircode;
    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean ownerOccupied;
}
