package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.util.List;

public interface WorkoutDAO
{
    Workout create(String workoutName, String workoutDesc, String expectedTarget);

    Workout getWorkoutById(int workoutId);

    List<Workout> getAllWorkouts();

    List<Workout> getFullBeginnerWorkouts();

    List<Workout> getFullIntermediateWorkouts();

    List<Workout> getFullExtremeWorkouts();

    List<Workout> getAllFullWorkouts();
}
