package com.techelevator.dao;

import com.techelevator.model.DifficultyLevel;
import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBeginnerDao implements BeginnerDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBeginnerDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DifficultyLevel create (int workoutId, int weight, String repSet, int expectedTime) {
        String sql = "INSERT INTO beginner_workouts(workout_id, weight, expected_rep_set, expected_time, difficulty) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "RETURNING beginner_id;";
        Integer beginnerId = jdbcTemplate.queryForObject(sql, Integer.class, workoutId, weight, repSet, expectedTime, "beginner");

        return getBeginnerLevelById(beginnerId);

    }
    @Override
    public DifficultyLevel getBeginnerLevelById(int beginnerId) {
        DifficultyLevel dL = null;
        String sql = "SELECT * " +
                "FROM beginner_workouts " +
                "WHERE beginner_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,beginnerId);

        if (results.next())
        {
            dL = mapRowToBeginner(results);
        }

        return dL;
    }

    @Override
    public List<DifficultyLevel> getAllBeginnerWorkouts()
    {
        List<DifficultyLevel> beginnerWorkouts = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM beginner_workouts;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next())
        {
            DifficultyLevel beginnerWorkout = mapRowToBeginner(results);
            beginnerWorkouts.add(beginnerWorkout);
        }

        return beginnerWorkouts;
    }

    @Override
    public DifficultyLevel updateBeginnerWorkout(int id, int workoutId, String weight, String repSet, int time)
    {
        String sql = "update beginner_workouts " +
                "SET weight = ?, expected_rep_set = ?, expected_time = ? " +
                "WHERE beginner_id = ?";

        DifficultyLevel beginner = getBeginnerLevelById(id);
        beginner.setWeight(weight);
        beginner.setRepSet(repSet);
        beginner.setExpectedTime(time);
        jdbcTemplate.update(sql, weight, repSet, time, id);

        return beginner;
    }

    @Override
    public boolean delete(int workoutId)
    {
        String sql = "DELETE " +
                "FROM beginner_workouts " +
                "WHERE workout_id = ?;";


        return jdbcTemplate.update(sql, workoutId) == 1;

    }

    private DifficultyLevel mapRowToBeginner (SqlRowSet rowSet) {
        DifficultyLevel difficultyLevel = new DifficultyLevel();
        difficultyLevel.setId(rowSet.getInt("beginner_id"));
        difficultyLevel.setWorkoutId(rowSet.getInt("workout_id"));
        difficultyLevel.setWeight(rowSet.getString("weight"));
        difficultyLevel.setRepSet(rowSet.getString("expected_rep_set"));
        difficultyLevel.setExpectedTime(rowSet.getInt("expected_time"));
        difficultyLevel.setDifficulty(rowSet.getString("difficulty"));
        return difficultyLevel;
    }



}
