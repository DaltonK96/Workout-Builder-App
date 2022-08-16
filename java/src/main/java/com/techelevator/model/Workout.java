package com.techelevator.model;

public class Workout {
    private int workoutId;
    private String workoutName;
    private String workoutDesc;
    private String expectedTarget;
    private String weight;
    private String repSet;
    private int time;
    private String difficulty;

    public Workout() {}

    public Workout(int workoutId, String workoutName, String workoutDesc, String expectedTarget) {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.workoutDesc = workoutName;
        this.expectedTarget = expectedTarget;
    }
    public Workout(int workoutId, String workoutName, String workoutDesc, String expectedTarget,
                   String weight, String repSet, int time, String difficulty)
    {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.workoutDesc = workoutName;
        this.expectedTarget = expectedTarget;
        this.weight = weight;
        this.repSet = repSet;
        this.time = time;
        this.difficulty = difficulty;
    }

    public int getWorkoutId() {
        return workoutId;
    }
    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }
    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutDesc() {
        return workoutDesc;
    }
    public void setWorkoutDesc(String workoutDesc) {
        this.workoutDesc = workoutDesc;
    }

    public String getExpectedTarget() {
        return expectedTarget;
    }
    public void setExpectedTarget(String expectedTarget) {
        this.expectedTarget = expectedTarget;
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

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public String getDifficulty() {return difficulty;}
    public void setDifficulty(String difficulty) {this.difficulty = difficulty;}






}
