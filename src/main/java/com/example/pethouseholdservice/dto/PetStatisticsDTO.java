package com.example.pethouseholdservice.dto;


public class PetStatisticsDTO {
    private double averageAge;
    private int oldestAge;
    private long totalCount;

    public PetStatisticsDTO(double averageAge, int oldestAge, long totalCount) {
        this.averageAge = averageAge;
        this.oldestAge = oldestAge;
        this.totalCount = totalCount;
    }

    public double getAverageAge() {
        return averageAge;
    }

    public int getOldestAge() {
        return oldestAge;
    }

    public long getTotalCount() {
        return totalCount;
    }
}
