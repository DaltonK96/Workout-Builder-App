package com.techelevator.dao;

import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcWorkoutsDao {

   private final JdbcTemplate jdbcTemplate;

   public JdbcWorkoutsDao(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
   }
   public boolean create(String workoutName, String workoutDesc, String expectedTarget) {
       String sql = "INSERT INTO workouts(workout_name, workout_desc, expected_target) " +
               "VALUES (?,?,?)";

   }

   private Workout mapRowToWorkout(SqlRowSet rowSet) {
       Workout workout = new Workout();
       workout.setWorkoutId(rowSet.getInt("workout_id"));
       workout.setWorkoutName(rowSet.getString("workout_name"));
       workout.setWorkoutDesc(rowSet.getString("workout_desc"));
       workout.setExpectedTarget(rowSet.getString("expected_target"));
       return workout;
   }
}
