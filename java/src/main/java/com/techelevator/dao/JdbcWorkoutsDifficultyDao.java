package com.techelevator.dao;

import com.techelevator.model.WorkoutDifficulties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWorkoutsDifficultyDao
{
    private final JdbcTemplate jdbcTemplate;

    public JdbcWorkoutsDifficultyDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WorkoutDifficulties> getWorkoutDifficulties()
    {
        List<WorkoutDifficulties> workoutDifficulties = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM workout_difficulties;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next())
        {
            WorkoutDifficulties difficulties = mapRowToDifficulty(results);
            workoutDifficulties.add(difficulties);
        }

        return workoutDifficulties;
    }

    private WorkoutDifficulties mapRowToDifficulty(SqlRowSet rowSet)
    {
        WorkoutDifficulties workoutDifficulties = new WorkoutDifficulties();
        workoutDifficulties.setWorkoutId(rowSet.getInt("workout_id"));
        workoutDifficulties.setBeginnerId(rowSet.getInt("beginner_id"));
        workoutDifficulties.setIntermediateId(rowSet.getInt("intermediate_id"));
        workoutDifficulties.setExtremeId(rowSet.getInt("extreme_id"));
        return workoutDifficulties;
    }
}
