package com.techelevator.dao;

import com.techelevator.model.Generator;

import java.util.List;

public interface WorkoutGeneratorDao
{
    int create();

    Generator create(int generatedWorkoutId, int workoutId, String difficulty, int userId);

    Generator getGeneratedWorkoutById(int id);

    List<Generator> createRandomWorkouts(String target, int time, int userId, String difficulty);

    Generator createRandomWorkout(String target, int userId, String difficulty, int generatedId);

}
