package com.techelevator.dao;

import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWorkoutsDao implements WorkoutDAO{

   private final JdbcTemplate jdbcTemplate;

   public JdbcWorkoutsDao(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
   }

   @Override
   public Workout create(String workoutName, String workoutDesc, String expectedTarget)
   {
       String sql = "INSERT INTO workouts(workout_name, workout_desc, expected_target) " +
               "VALUES (?,?,?)" +
               "RETURNING workout_id";

       Integer id = jdbcTemplate.queryForObject(sql, Integer.class, workoutName, workoutDesc, expectedTarget);

       return getWorkoutId(id);
   }

   @Override
   public Workout getWorkoutId(int workoutId)
   {
       Workout workout = null;

       String sql = "SELECT * " +
               "FROM workouts " +
               "WHERE workout_id = ?";

       SqlRowSet results = jdbcTemplate.queryForRowSet(sql,workoutId);

       if (results.next())
       {
           workout = mapRowToWorkout(results);
       }

       return workout;
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