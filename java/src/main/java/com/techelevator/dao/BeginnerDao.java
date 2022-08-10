package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;

public interface BeginnerDao {
    DifficultyLevel create (int workoutId, int weight, String repSet, int expectedTime);

    DifficultyLevel getBeginnerLevelById(int beginnerId);
}
