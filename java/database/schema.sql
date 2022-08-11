BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS workouts;
DROP TABLE IF EXISTS beginner_workouts;
DROP TABLE IF EXISTS intermediate_workouts;
DROP TABLE IF EXISTS extreme_workouts;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE workouts (
    workout_id SERIAL,
    workout_name varchar(50) NOT NULL UNIQUE,
    workout_desc varchar(500) NOT NULL,
    expected_target varchar(100),
    CONSTRAINT valid_target CHECK(expected_target IN ('arms','legs','abs','back','cardio','total body')),
    CONSTRAINT PK_workout PRIMARY KEY (workout_id)
);

CREATE TABLE beginner_workouts (
    beginner_id SERIAL,
    workout_id SERIAL,
    weight varchar(20),
    expected_rep_set varchar(100) NOT NULL,
    expected_time int,
    CONSTRAINT FK_workout FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
    CONSTRAINT PK_beginner PRIMARY KEY (beginner_id)
    );

CREATE TABLE intermediate_workouts (
        intermediate_id SERIAL,
        workout_id SERIAL,
        weight varchar(20),
        expected_rep_set varchar(100) NOT NULL,
        expected_time int,
        CONSTRAINT FK_workout FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
        CONSTRAINT PK_intermediate PRIMARY KEY (intermediate_id)
        );

 CREATE TABLE extreme_workouts (
         extreme_id SERIAL,
         workout_id SERIAL,
         weight varchar(20),
         expected_rep_set varchar(100) NOT NULL,
         expected_time int,
         CONSTRAINT FK_workout FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
         CONSTRAINT PK_extreme PRIMARY KEY (extreme_id)
          );

COMMIT TRANSACTION;
