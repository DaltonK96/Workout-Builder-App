package com.techelevator.controller;

import com.techelevator.dao.BeginnerDao;
import com.techelevator.dao.ExtremeDao;
import com.techelevator.dao.IntermediateDao;
import com.techelevator.dao.WorkoutDAO;
import com.techelevator.model.DifficultyLevel;
import com.techelevator.model.DifficultyLevelDTO;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class WorkoutController
{
    private WorkoutDAO workoutDAO;
    private BeginnerDao beginnerDao;
    private IntermediateDao intermediateDao;
    private ExtremeDao extremeDao;

    public WorkoutController(WorkoutDAO workoutDAO, BeginnerDao beginnerDao, IntermediateDao intermediateDao, ExtremeDao extremeDao)
    {
        this.workoutDAO = workoutDAO;
        this.beginnerDao = beginnerDao;
        this.intermediateDao = intermediateDao;
        this.extremeDao = extremeDao;
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

    @RequestMapping(path = "/workouts/intermediate", method = RequestMethod.POST)
    public DifficultyLevel newIntermediateWorkout(@RequestBody DifficultyLevelDTO difficultyLevelDTO)
    {
        DifficultyLevel difficultyLevel = intermediateDao.create(difficultyLevelDTO.getWorkoutId(),
                difficultyLevelDTO.getWeight(), difficultyLevelDTO.getRepSet(),difficultyLevelDTO.getExpectedTime());

        return difficultyLevel;
    }

    @RequestMapping(path = "/workouts/extreme", method = RequestMethod.POST)
    public DifficultyLevel newExtremeWorkout(@RequestBody DifficultyLevelDTO difficultyLevelDTO)
    {
        DifficultyLevel difficultyLevel = extremeDao.create(difficultyLevelDTO.getWorkoutId(),
                difficultyLevelDTO.getWeight(), difficultyLevelDTO.getRepSet(),difficultyLevelDTO.getExpectedTime());

        return difficultyLevel;
    }

    @RequestMapping(path = "/workouts", method = RequestMethod.GET)
    public List<Workout> listWorkouts()
    {
        return workoutDAO.getAllWorkouts();
    }

    @RequestMapping(path = "/workouts/beginner", method = RequestMethod.GET)
    public List<Workout> listBeginnerWorkouts()
    {
        return workoutDAO.getFullBeginnerWorkouts();
    }

    @RequestMapping(path = "/workouts/intermediate", method = RequestMethod.GET)
    public List<Workout> listIntermediateWorkouts()
    {
        return workoutDAO.getFullIntermediateWorkouts();
    }

    @RequestMapping(path = "/workouts/extreme", method = RequestMethod.GET)
    public List<Workout> listExtremeWorkouts()
    {
        return workoutDAO.getFullExtremeWorkouts();
    }

    @RequestMapping(path= "/workouts/all", method = RequestMethod.GET)
    public List<Workout> listAllWorkouts()
    {
        return workoutDAO.getAllFullWorkouts();
    }

}
