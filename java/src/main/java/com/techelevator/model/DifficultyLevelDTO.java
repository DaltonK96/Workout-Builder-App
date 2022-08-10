package com.techelevator.model;

public class DifficultyLevelDTO
{
    private int id;
    private int workoutId;
    private int weight;
    private String repSet;
    private int expectedTime;


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getWorkoutId() {return workoutId;}
    public void setWorkoutId(int workoutId) {this.workoutId = workoutId;}

    public int getWeight() {return weight;}
    public void setWeight(int weight) {this.weight = weight;}

    public String getRepSet() {return repSet;}
    public void setRepSet(String repSet) {this.repSet = repSet;}

    public int getExpectedTime() {return expectedTime;}
    public void setExpectedTime(int expectedTime) {this.expectedTime = expectedTime;}


    @Override
    public String toString()
    {
        return "DifficultyLevelDTO{" +
                "id='" + id + '\'' +
                ", workoutId='" + '\'' +
                ", weight='" + weight + '\'' +
                ", repSet='" + repSet + '\'' +
                ", expectedTime'" + expectedTime + '\'' +
                "}";
    }

}
