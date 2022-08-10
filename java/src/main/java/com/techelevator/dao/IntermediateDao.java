package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;

public interface IntermediateDao
{
    DifficultyLevel create (int workoutId, int weight, String repSet, int expectedTime);

    DifficultyLevel getIntermediateLevelById(int intermediateId);
}
