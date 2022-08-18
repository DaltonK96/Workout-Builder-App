package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    private WorkoutGeneratorDao workoutGeneratorDao;
    private UserDao userDao;

    public WorkoutController(WorkoutDAO workoutDAO, BeginnerDao beginnerDao, IntermediateDao intermediateDao, ExtremeDao extremeDao, WorkoutGeneratorDao workoutGeneratorDao, UserDao userDao)
    {
        this.workoutDAO = workoutDAO;
        this.beginnerDao = beginnerDao;
        this.intermediateDao = intermediateDao;
        this.extremeDao = extremeDao;
        this.workoutGeneratorDao = workoutGeneratorDao;
        this.userDao = userDao;
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

    @RequestMapping(path = "/workouts/target/{target}", method = RequestMethod.GET)
    public List<Workout> listWorkoutByTarget(@PathVariable("target") String target)
    {
        return workoutDAO.getWorkoutByTarget(target);
    }

    @RequestMapping(path = "/workouts/time/{time}", method = RequestMethod.GET)
    public List<Workout> listWorkoutByTime(@PathVariable("time") int time)
    {
        return workoutDAO.getWorkoutByTime(time);
    }

    @RequestMapping(path = "/workouts/updatedWorkout", method = RequestMethod.PUT)
    public Workout updateWorkout(@RequestBody WorkoutDTO workoutDTO)
    {
        return workoutDAO.updateWorkout(workoutDTO.getWorkoutId(),workoutDTO.getWorkoutName(),
                workoutDTO.getWorkoutDesc(),workoutDTO.getExpectedTarget());
    }

    @RequestMapping(path = "/workouts/updatedBeginner", method = RequestMethod.PUT)
    public DifficultyLevel updateBeginnerWorkout(@RequestBody DifficultyLevel difficultyLevel)
    {
        return beginnerDao.updateBeginnerWorkout(difficultyLevel.getId(),difficultyLevel.
                getWorkoutId(),difficultyLevel.getWeight(),difficultyLevel.getRepSet(),difficultyLevel.getExpectedTime());
    }

    @RequestMapping(path = "/workouts/updatedIntermediate", method = RequestMethod.PUT)
    public DifficultyLevel updateIntermediateWorkout(@RequestBody DifficultyLevel difficultyLevel)
    {
        return intermediateDao.updateIntermediateWorkout(difficultyLevel.getId(),difficultyLevel.
                getWorkoutId(),difficultyLevel.getWeight(),difficultyLevel.getRepSet(),difficultyLevel.getExpectedTime());
    }

    @RequestMapping(path = "/workouts/updatedExtreme", method = RequestMethod.PUT)
    public DifficultyLevel updateExtremeWorkout(@RequestBody DifficultyLevel difficultyLevel)
    {
        return extremeDao.updateExtremeWorkout(difficultyLevel.getId(),difficultyLevel.
                getWorkoutId(),difficultyLevel.getWeight(),difficultyLevel.getRepSet(),difficultyLevel.getExpectedTime());
    }

    @RequestMapping(path = "/workouts/{id}", method = RequestMethod.GET)
    public Workout getWorkoutById(@PathVariable ("id") int id) {
        return workoutDAO.getWorkoutById(id);
    }

    @RequestMapping(path = "/randomWorkouts", method = RequestMethod.POST)
    public List<Generator> createRandomWorkout(Principal user, @RequestBody GeneratorDTO generatorDTO)
    {

        return workoutGeneratorDao.createRandomWorkouts(generatorDTO.getTarget(), generatorDTO.getTime(), userDao.findIdByUsername(user.getName()), generatorDTO.getDifficulty());
    }

    @RequestMapping(path = "/workouts/history", method = RequestMethod.GET)
    public List<Generator> getWorkoutHistoryByUser(Principal user)
    {
        return workoutGeneratorDao.getWorkoutHistory(userDao.findIdByUsername(user.getName()));
    }

    @RequestMapping(path = "/workouts/randomlyGenerated/{id}", method = RequestMethod.GET)
    public List<Workout> getFullRandomlyGenWorkouts(@PathVariable ("id") int id)
    {
        return workoutGeneratorDao.listOfGeneratedWorkouts(id);
    }

    @RequestMapping(path = "/workouts/{id}", method = RequestMethod.DELETE)
    public void getDeleteWorkouts (@PathVariable ("id") int id)
    {
        beginnerDao.delete(id);
        intermediateDao.delete(id);
        extremeDao.delete(id);
        workoutGeneratorDao.delete(id);
        workoutDAO.delete(id);
    }
}
