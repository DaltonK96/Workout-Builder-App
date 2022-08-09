package com.techelevator.model;

public class Workout {
    private int workoutId;
    private String workoutName;
    private String workoutDesc;
    private String expectedTarget;

    public Workout() {
    };

    public Workout(int workoutId, String workoutName, String workoutDesc, String expectedTarget) {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.workoutDesc = workoutName;
        this.expectedTarget = expectedTarget;
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






}
