package com.techelevator.model;

public class Generator
{
    private int generatedId;
    private int randomizedId;
    private int workoutId;
    private String difficulty;
    private int userId;

    public Generator(){}

    public Generator(int generatedId)
    {
        this.generatedId = generatedId;
    }

    public Generator(int generatedId, int randomizedId, int workoutId, String difficulty, int userId)
    {
        this.generatedId = generatedId;
        this.randomizedId = randomizedId;
        this.workoutId = workoutId;
        this.difficulty = difficulty;
        this.userId = userId;
    }

    public Generator(int generatedId, int workoutId, String difficulty, int userId)
    {
        this.generatedId = generatedId;
        this.workoutId = workoutId;
        this.difficulty = difficulty;
        this.userId = userId;
    }

    public int getGeneratedId() {return generatedId;}
    public void setGeneratedId(int generatedId) {this.generatedId = generatedId;}

    public int getRandomizedId() {return randomizedId;}
    public void setRandomizedId(int randomizedId) {this.randomizedId = randomizedId;}

    public int getWorkoutId() {return workoutId;}
    public void setWorkoutId(int workoutId) {this.workoutId = workoutId;}

    public String getDifficulty() {return difficulty;}
    public void setDifficulty(String difficulty) {this.difficulty = difficulty;}

    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

}
