package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;

import java.util.List;

public interface BeginnerDao {
    DifficultyLevel create (int workoutId, int weight, String repSet, int expectedTime);

    DifficultyLevel getBeginnerLevelById(int beginnerId);

    List<DifficultyLevel> getAllBeginnerWorkouts();

    DifficultyLevel updateBeginnerWorkout(int id, int workoutId, String weight, String repSet, int time);

    boolean delete(int workoutId);
}
