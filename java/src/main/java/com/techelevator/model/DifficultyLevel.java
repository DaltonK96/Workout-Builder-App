package com.techelevator.model;

public class DifficultyLevel {
    private int id;
    private int workoutId;
    private String weight;
    private String repSet;
    private int expectedTime;

    public DifficultyLevel(int id, int workoutId, String weight, String repSet, int expectedTime) {
        this.id = id;
        this.workoutId = workoutId;
        this.weight = weight;
        this.repSet = repSet;
        this.expectedTime = expectedTime;
    }
    public DifficultyLevel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRepSet() {
        return repSet;
    }

    public void setRepSet(String repSet) {
        this.repSet = repSet;
    }

    public int getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }


}


