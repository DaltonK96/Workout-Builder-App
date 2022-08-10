package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
        String sql = "INSERT INTO intermediate_workouts(workout_id, weight, expected_rep_set, expected_tim) " +
                "VALUES (?, ?, ?, ?)" +
                "RETURNING intermediate_id;";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, workoutId, weight, repSet, expectedTime);

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

    private DifficultyLevel mapRowToIntermediate (SqlRowSet rowSet)
    {
        DifficultyLevel difficultyLevel = new DifficultyLevel();
        difficultyLevel.setId(rowSet.getInt("intermediate_id"));
        difficultyLevel.setWorkoutId(rowSet.getInt("workout_id"));
        difficultyLevel.setWeight(rowSet.getInt("weight"));
        difficultyLevel.setRepSet(rowSet.getString("expected_rep_set"));
        difficultyLevel.setExpectedTime(rowSet.getInt("expected_time"));
        return difficultyLevel;
    }
}
