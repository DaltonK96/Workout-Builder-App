package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;

import java.util.List;

public interface ExtremeDao
{
    DifficultyLevel create(int workoutId, int weight, String repSet, int expectedTime);

    DifficultyLevel getExtremeLevelById(int extremeId);

    public List<DifficultyLevel> getAllExtremeWorkouts();
}
