package com.techelevator.model;

public class WorkoutDTO
{
    private int workoutId;
    private String workoutName;
    private String workoutDesc;
    private String expectedTarget;

    public int getWorkoutId() {return workoutId;}
    public void setWorkoutId(int workoutId) {this.workoutId = workoutId;}

    public String getWorkoutName(){return workoutName;}
    public void setWorkoutName(){this.workoutName = workoutName;}

    public String getWorkoutDesc() {return workoutDesc;}
    public void setWorkoutDesc(String workoutDesc) {this.workoutDesc = workoutDesc;}

    public String getExpectedTarget() {return expectedTarget;}
    public void setExpectedTarget(String expectedTarget) {this.expectedTarget = expectedTarget;}

    @Override
    public String toString()
    {
        return "WorkoutDTO{" +
                "workoutId='" + workoutId + '\'' +
                ", workoutName='" + workoutName + '\'' +
                ", workoutDesc='" + workoutDesc + '\'' +
                ", expectedTarget='" + expectedTarget + '\'' +
                "}";
    }

}
