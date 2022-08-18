package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;
import com.techelevator.model.Generator;
import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class JdbcWorkoutGeneratorDao implements WorkoutGeneratorDao
{
    private final JdbcTemplate jdbcTemplate;
    private WorkoutDAO workoutDAO;

    public JdbcWorkoutGeneratorDao(JdbcTemplate jdbcTemplate, WorkoutDAO workoutDAO)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.workoutDAO = workoutDAO;
    }

    private int newGeneratedId()
    {
        int id = 0;

        String sql = "SELECT * " +
                "FROM generate_new_workout_id " +
                "ORDER BY generated_workout_id DESC " +
                "LIMIT 1;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        if (results.next())
        {
            id = mapRowToInt(results);
        }

        return id +1;
    }

    @Override
    public int create()
    {
        int id = 0;

        String sql = "INSERT INTO generate_new_workout_id " +
                "VALUES (?) " +
                "RETURNING generated_workout_id;";

        int genId =  newGeneratedId();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genId);

        if (results.next())
        {
            id = mapRowToInt(results);
        }

        return id;
    }

    @Override
    public Generator create(int generatedWorkoutId, int workoutId, String difficulty, int userId)
    {
        String sql = "INSERT INTO generated_workouts (generated_workout_id, workout_id, difficulty, user_id) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING randomized_id;";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, generatedWorkoutId, workoutId, difficulty, userId);

        return getGeneratedWorkoutById(id);
    }

    @Override
    public Generator getGeneratedWorkoutById(int id)
    {
        Generator generator = null;
        String sql = "SELECT * " +
                "FROM generated_workouts " +
                "WHERE randomized_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);

        if (results.next())
        {
            generator = mapRowToGenerator(results);
        }

        return generator;
    }

    @Override
    public List<Generator> createRandomWorkouts(String target, int time, int userId, String difficulty)
    {
        int generatedId = create();
        int timeCounter = 0;

        List<Generator> generatedWorkouts = new ArrayList<>();

        while (timeCounter <= time - 10 && timeCounter <= time + 10)
        {
            Generator generator = createRandomWorkout(target, userId, difficulty, generatedId);
            int timeToAdd = workoutDAO.getTimeByIdAndDifficulty(generator.getWorkoutId(), difficulty);
            timeCounter += timeToAdd;
            generatedWorkouts.add(generator);

        }

        return generatedWorkouts;
    }

    @Override
    public Generator createRandomWorkout(String target, int userId, String difficulty, int generatedId)
    {
        List<Workout> allWorkouts = workoutDAO.getWorkoutByTarget(target);
        List<Integer> workoutIds = new ArrayList<>();

        for (Workout workout : allWorkouts)
        {
            int workoutId = workout.getWorkoutId();
            workoutIds.add(workoutId);
        }

        Random random = new Random();
        int randomWorkoutId = workoutIds.get(random.nextInt(workoutIds.size()));

        create(generatedId, randomWorkoutId, difficulty, userId);

        Generator generator = new Generator(generatedId, randomWorkoutId, difficulty, userId);

        return generator;

    }

    @Override
    public List<Workout> listOfGeneratedWorkouts(int id)
    {
        List<Workout> workouts = new ArrayList<>();

        List<Generator> generatedWorkouts = getWorkoutIdsByGeneratedId(id);

        String difficulty = "";
        List<Integer> workoutIds = new ArrayList<>();

        for (Generator workout : generatedWorkouts)
        {
            int workoutId = workout.getWorkoutId();
            difficulty = workout.getDifficulty();
            workoutIds.add(workoutId);
        }

        List<Workout> allWorkouts = workoutDAO.getAllFullWorkouts();

        for (int workoutId : workoutIds)
        {
            for (Workout workout : allWorkouts)
            {
                if (workoutId == workout.getWorkoutId() && difficulty.equals(workout.getDifficulty()))
                {
                    workouts.add(workout);
                    break;
                }
            }
        }

        return workouts;
    }

    public List<Generator> getWorkoutIdsByGeneratedId(int id)
    {
        List<Generator> workouts = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM generated_workouts " +
                "WHERE generated_workout_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);

        while (results.next())
        {
            Generator workout = mapRowToGenerator(results);
            workouts.add(workout);

        }

        return workouts;
    }

    public List<Generator> getWorkoutHistory(int id)
    {
        List<Generator> history = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM generated_workouts " +
                "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while (results.next())
        {
            Generator generator = mapRowToGenerator(results);
            history.add(generator);
        }

        return history;
    }

    private int mapRowToInt (SqlRowSet rowSet)
    {
        int id = rowSet.getInt("generated_workout_id");

        return id;
    }

    @Override
    public boolean delete(int workoutId)
    {
        String sql = "DELETE " +
                "FROM generated_workouts " +
                "WHERE workout_id = ?;";


        return jdbcTemplate.update(sql, workoutId) == 1;

    }

    private Generator mapRowToGenerator(SqlRowSet rowSet)
    {
        Generator generator = new Generator();
        generator.setRandomizedId(rowSet.getInt("randomized_id"));
        generator.setGeneratedId(rowSet.getInt("generated_workout_id"));
        generator.setWorkoutId(rowSet.getInt("workout_id"));
        generator.setDifficulty(rowSet.getString("difficulty"));
        generator.setUserId(rowSet.getInt("user_id"));
        return generator;
    }
}
