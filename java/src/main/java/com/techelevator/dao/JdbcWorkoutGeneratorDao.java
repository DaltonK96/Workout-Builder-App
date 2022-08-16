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

    @Override
    public int create()
    {
        String sql = "INSERT INTO generate_new_workout_id " +
                "VALUES () " +
                "RETURNING generated_workout_id";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class);

        return id;
    }

    @Override
    public Generator create(int generatedWorkoutId, int workoutId, String difficulty, int userId)
    {
        String sql = "INSERT INTO generated_workouts (generated_workout_id, workout_id, difficulty, user_id " +
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

        while (timeCounter <= time - 10 && timeCounter >= time + 10)
        {
            Generator generator = createRandomWorkout(target, userId, difficulty, generatedId);
            timeCounter += workoutDAO.getTimeByIdAndDifficulty(generator.getWorkoutId(), difficulty);
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
