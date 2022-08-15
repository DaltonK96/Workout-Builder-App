package com.techelevator.dao;

import com.techelevator.model.Generator;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcWorkoutGeneratorDao
{
    private final JdbcTemplate jdbcTemplate;

    public JdbcWorkoutGeneratorDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create()
    {
        String sql = "INSERT INTO generate_new_workout_id " +
                "VALUES () " +
                "RETURNING generated_workout_id";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class);

        return id;
    }
}
