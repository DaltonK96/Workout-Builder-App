package com.techelevator.model;

public class WorkoutDifficulties
{
    private int workoutId;
    private int beginnerId;
    private int intermediateId;
    private int extremeId;

    public WorkoutDifficulties(){}

    public WorkoutDifficulties(int workoutId, int beginnerId, int intermediateId, int extremeId)
    {
        this.workoutId = workoutId;
        this.beginnerId = beginnerId;
        this.intermediateId = intermediateId;
        this.extremeId = extremeId;
    }

    public int getWorkoutId() {return workoutId;}
    public void setWorkoutId(int workoutId) {this.workoutId = workoutId;}

    public int getBeginnerId() {return beginnerId;}
    public void setBeginnerId(int beginnerId) {this.beginnerId = beginnerId;}

    public int getIntermediateId() {return intermediateId;}
    public void setIntermediateId(int intermediateId) {this.intermediateId = intermediateId;}

    public int getExtremeId() {return extremeId;}
    public void setExtremeId(int extremeId) {this.extremeId = extremeId;}


}
