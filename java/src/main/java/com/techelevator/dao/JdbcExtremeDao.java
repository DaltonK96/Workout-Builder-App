package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcExtremeDao implements ExtremeDao
{
    private final JdbcTemplate jdbcTemplate;

    public JdbcExtremeDao (JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public DifficultyLevel create(int workoutId, int weight, String repSet, int expectedTime)
    {
        String sql = "INSERT INTO extreme_workouts(workout_id, weight, expected_rep_set, expected_tim) " +
                "VALUES (?, ?, ?, ?)" +
                "RETURNING extreme_id;";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, workoutId, weight, repSet, expectedTime);

        return getExtremeLevelById(id);
    }

    @Override
    public DifficultyLevel getExtremeLevelById(int extremeId) {
        DifficultyLevel dL = null;
        String sql = "SELECT * " +
                "FROM extreme_workouts " +
                "WHERE extreme_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,extremeId);

        if (results.next())
        {
            dL = mapRowToExtreme(results);
        }

        return dL;
    }

    @Override
    public List<DifficultyLevel> getAllExtremeWorkouts()
    {
        List<DifficultyLevel> extremeWorkouts = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM extreme_workouts;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next())
        {
            DifficultyLevel extremeWorkout = mapRowToExtreme(results);
            extremeWorkouts.add(extremeWorkout);
        }

        return extremeWorkouts;
    }

    private DifficultyLevel mapRowToExtreme (SqlRowSet rowSet)
    {
        DifficultyLevel difficultyLevel = new DifficultyLevel();
        difficultyLevel.setId(rowSet.getInt("extreme_id"));
        difficultyLevel.setWorkoutId(rowSet.getInt("workout_id"));
        difficultyLevel.setWeight(rowSet.getString("weight"));
        difficultyLevel.setRepSet(rowSet.getString("expected_rep_set"));
        difficultyLevel.setExpectedTime(rowSet.getInt("expected_time"));
        return difficultyLevel;
    }
}
