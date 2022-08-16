package com.techelevator.model;

public class GeneratorDTO
{
    private String target;
    private int time;
    private String difficulty;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    @Override
    public String toString() {
        return "GeneratorDTO{" +
                "target='" + target + '\'' +
                ", time='" + '\'' +
                ", difficulty='" + difficulty + '\'' +
                "}";
    }


    
}
