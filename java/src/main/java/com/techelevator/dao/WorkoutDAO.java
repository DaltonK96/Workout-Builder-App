package com.techelevator.dao;

import com.techelevator.model.Workout;

public interface WorkoutDAO
{
    Workout create(String workoutName, String workoutDesc, String expectedTarget);

    Workout getWorkoutId(int workoutId);
}
