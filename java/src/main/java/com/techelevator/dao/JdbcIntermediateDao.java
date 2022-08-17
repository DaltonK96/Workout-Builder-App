package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIntermediateDao implements IntermediateDao
{
    private final JdbcTemplate jdbcTemplate;

    public JdbcIntermediateDao (JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DifficultyLevel create (int workoutId, int weight, String repSet, int expectedTime)
    {
        String sql = "INSERT INTO intermediate_workouts(workout_id, weight, expected_rep_set, expected_time, difficulty) " +
                "VALUES (?, ?, ?, ?, ?)" +
                "RETURNING intermediate_id;";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, workoutId, weight, repSet, expectedTime, "intermediate");

        return getIntermediateLevelById(id);
    }

    @Override
    public DifficultyLevel getIntermediateLevelById(int intermediateId) {
        DifficultyLevel dL = null;
        String sql = "SELECT * " +
                "FROM intermediate_workouts " +
                "WHERE intermediate_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,intermediateId);

        if (results.next())
        {
            dL = mapRowToIntermediate(results);
        }

        return dL;
    }

    @Override
    public List<DifficultyLevel> getAllIntermediateWorkouts()
    {
        List<DifficultyLevel> intermediateWorkouts = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM intermediate_workouts;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next())
        {
            DifficultyLevel intermediateWorkout = mapRowToIntermediate(results);
            intermediateWorkouts.add(intermediateWorkout);
        }

        return intermediateWorkouts;
    }

    @Override
    public DifficultyLevel updateIntermediateWorkout(int id, int workoutId, String weight, String repSet, int time)
    {
        String sql = "update intermediate_workouts " +
                "SET weight = ?, expected_rep_set = ?, expected_time = ? " +
                "WHERE intermediate_id = ?";

        DifficultyLevel intermediate = getIntermediateLevelById(id);
        intermediate.setWeight(weight);
        intermediate.setRepSet(repSet);
        intermediate.setExpectedTime(time);
        jdbcTemplate.update(sql, weight, repSet, time, id);

        return intermediate;
    }

    @Override
    public boolean delete(int workoutId)
    {
        String sql = "DELETE " +
                "FROM intermediate_workouts " +
                "WHERE workout_id = ?;";


        return jdbcTemplate.update(sql, workoutId) == 1;

    }

    private DifficultyLevel mapRowToIntermediate (SqlRowSet rowSet)
    {
        DifficultyLevel difficultyLevel = new DifficultyLevel();
        difficultyLevel.setId(rowSet.getInt("intermediate_id"));
        difficultyLevel.setWorkoutId(rowSet.getInt("workout_id"));
        difficultyLevel.setWeight(rowSet.getString("weight"));
        difficultyLevel.setRepSet(rowSet.getString("expected_rep_set"));
        difficultyLevel.setExpectedTime(rowSet.getInt("expected_time"));
        difficultyLevel.setDifficulty(rowSet.getString("difficulty"));
        return difficultyLevel;
    }
}
