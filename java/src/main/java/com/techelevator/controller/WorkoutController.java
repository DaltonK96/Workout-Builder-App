package com.techelevator.controller;

import com.techelevator.dao.BeginnerDao;
import com.techelevator.dao.IntermediateDao;
import com.techelevator.dao.WorkoutDAO;
import com.techelevator.model.DifficultyLevel;
import com.techelevator.model.DifficultyLevelDTO;
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
    private BeginnerDao beginnerDao;
    private IntermediateDao intermediateDao;

    public WorkoutController(WorkoutDAO workoutDAO, BeginnerDao beginnerDao, IntermediateDao intermediateDao)
    {
        this.workoutDAO = workoutDAO;
        this.beginnerDao = beginnerDao;
        this.intermediateDao = intermediateDao;
    }

    @RequestMapping(path = "/workouts", method = RequestMethod.POST)
    public Workout newWorkout(@RequestBody WorkoutDTO workoutDTO)
    {
        Workout workout = workoutDAO.create(workoutDTO.getWorkoutName(),workoutDTO.getWorkoutDesc(),workoutDTO.getExpectedTarget());

        return workout;
    }


    @RequestMapping(path = "/workouts/beginner", method = RequestMethod.POST)
    public DifficultyLevel newBeginnerWorkout(@RequestBody DifficultyLevelDTO difficultyLevelDTO)
    {
        DifficultyLevel difficultyLevel = beginnerDao.create(difficultyLevelDTO.getWorkoutId(),
                difficultyLevelDTO.getWeight(), difficultyLevelDTO.getRepSet(),difficultyLevelDTO.getExpectedTime());

        return difficultyLevel;
    }

    @RequestMapping(path = "workouts/intermediate", method = RequestMethod.POST)
    public DifficultyLevel newIntermediateWorkout(RequestBody DifficultyLevelDTO difficultyLevelDTO)
    {
        DifficultyLevel difficultyLevel = intermediateDao.create(difficultyLevelDTO.getWorkoutId(),
                difficultyLevelDTO.getWeight(), difficultyLevelDTO.getRepSet(),difficultyLevelDTO.getExpectedTime());

        return difficultyLevel;
    }

}
