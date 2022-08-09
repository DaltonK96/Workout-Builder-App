package com.techelevator.controller;

import com.techelevator.dao.WorkoutDAO;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@PreAuthorize("isAuthenticated()")
@RestController
public class WorkoutController
{
    private WorkoutDAO workoutDAO;

    public WorkoutController(WorkoutDAO workoutDAO)
    {
        this.workoutDAO = workoutDAO;
    }

    @RequestMapping(path = "/workouts", method = RequestMethod.POST)
    public Workout newWorkout(@RequestBody WorkoutDTO workoutDTO)
    {
        Workout workout = workoutDAO.create(workoutDTO.getWorkoutName(),workoutDTO.getWorkoutDesc(),workoutDTO.getExpectedTarget());

        return workout;
    }


}
