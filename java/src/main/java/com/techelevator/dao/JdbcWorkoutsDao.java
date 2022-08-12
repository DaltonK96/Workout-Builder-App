package com.techelevator.dao;

import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
               "RETURNING workout_id;";

       Integer id = jdbcTemplate.queryForObject(sql, Integer.class, workoutName, workoutDesc, expectedTarget);

       return getWorkoutById(id);
   }

   @Override
   public Workout getWorkoutById(int workoutId)
   {
       Workout workout = null;

       String sql = "SELECT * " +
               "FROM workouts " +
               "WHERE workout_id = ?;";

       SqlRowSet results = jdbcTemplate.queryForRowSet(sql,workoutId);

       if (results.next())
       {
           workout = mapRowToWorkout(results);
       }

       return workout;
   }

   @Override
   public List<Workout> getAllWorkouts()
   {
       List<Workout> workouts = new ArrayList<>();

       String sql = "SELECT * " +
               "FROM workouts;";

       SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

       while (results.next())
       {
           Workout workout = mapRowToWorkout(results);
           workouts.add(workout);
       }

       return workouts;
   }

   @Override
   public List<Workout> getFullBeginnerWorkouts() {
       List<Workout> workouts = new ArrayList<>();
       String sql = "SELECT workout_name, workout_desc, expected_target, beginner_workouts.weight, beginner_workouts.expected_rep_set, beginner_workouts.expected_time " +
               "FROM workouts " + "JOIN beginner_workouts ON workouts.workout_id = beginner_workouts.workout_id;";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

       while (results.next())
       {
           Workout workout = mapRowToFullWorkout(results);
           workouts.add(workout);
       }

       return workouts;
   }

    @Override
    public List<Workout> getFullIntermediateWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT workout_name, workout_desc, expected_target, intermediate_workouts.weight, intermediate_workouts.expected_rep_set, intermediate_workouts.expected_time " +
                "FROM workouts " + "JOIN intermediate_workouts ON workouts.workout_id = intermediate_workouts.workout_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next())
        {
            Workout workout = mapRowToFullWorkout(results);
            workouts.add(workout);
        }

        return workouts;
    }

    @Override
    public List<Workout> getFullExtremeWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT workout_name, workout_desc, expected_target, extreme_workouts.weight, extreme_workouts.expected_rep_set, extreme_workouts.expected_time " +
                "FROM workouts " + "JOIN extreme_workouts ON workouts.workout_id = extreme_workouts.workout_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next())
        {
            Workout workout = mapRowToFullWorkout(results);
            workouts.add(workout);
        }

        return workouts;
    }

    @Override
    public List<Workout> getAllFullWorkouts() {
        List<Workout> beginnerWorkouts = getFullBeginnerWorkouts();
        List<Workout> intermediateWorkouts = getFullIntermediateWorkouts();
        List<Workout> extremeWorkouts = getFullExtremeWorkouts();
        List<Workout> allWorkouts = new ArrayList<>();
        allWorkouts.addAll(beginnerWorkouts);
        allWorkouts.addAll(intermediateWorkouts);
        allWorkouts.addAll(extremeWorkouts);

        return allWorkouts;
    }

   private Workout mapRowToWorkout(SqlRowSet rowSet) {
       Workout workout = new Workout();
       workout.setWorkoutId(rowSet.getInt("workout_id"));
       workout.setWorkoutName(rowSet.getString("workout_name"));
       workout.setWorkoutDesc(rowSet.getString("workout_desc"));
       workout.setExpectedTarget(rowSet.getString("expected_target"));
       return workout;
   }
    private Workout mapRowToFullWorkout(SqlRowSet rowSet) {
        Workout workout = new Workout();
        workout.setWorkoutName(rowSet.getString("workout_name"));
        workout.setWorkoutDesc(rowSet.getString("workout_desc"));
        workout.setExpectedTarget(rowSet.getString("expected_target"));
        workout.setWeight(rowSet.getString("weight"));
        workout.setRepSet(rowSet.getString("expected_rep_set"));
        workout.setTime(rowSet.getInt("expected_time"));
        return workout;
    }
}
