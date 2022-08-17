package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;

import java.util.List;

public interface IntermediateDao
{
    DifficultyLevel create (int workoutId, int weight, String repSet, int expectedTime);

    DifficultyLevel getIntermediateLevelById(int intermediateId);

    List<DifficultyLevel> getAllIntermediateWorkouts();

    DifficultyLevel updateIntermediateWorkout(int id, int workoutId, String weight, String repSet, int time);

    boolean delete(int workoutId);
}
