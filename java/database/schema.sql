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
    expected_rep_set varchar(100),
    expected_time int,
    difficulty varchar(100),
    CONSTRAINT valid_difficulty CHECK (difficulty IN ('beginner')),
    CONSTRAINT FK_workout FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
    CONSTRAINT PK_beginner PRIMARY KEY (beginner_id)
    );

CREATE TABLE intermediate_workouts (
        intermediate_id SERIAL,
        workout_id SERIAL,
        weight varchar(20),
        expected_rep_set varchar(100) NOT NULL,
        expected_time int,
        difficulty varchar(100),
        CONSTRAINT valid_difficulty CHECK (difficulty IN ('intermediate')),
        CONSTRAINT FK_workout FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
        CONSTRAINT PK_intermediate PRIMARY KEY (intermediate_id)
        );

 CREATE TABLE extreme_workouts (
         extreme_id SERIAL,
         workout_id SERIAL,
         weight varchar(20),
         expected_rep_set varchar(100) NOT NULL,
         expected_time int,
         difficulty varchar(100),
         CONSTRAINT valid_difficulty CHECK (difficulty IN ('extreme')),
         CONSTRAINT FK_workout FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
         CONSTRAINT PK_extreme PRIMARY KEY (extreme_id)
          );

          CREATE TABLE generate_new_workout_id (
          generated_workout_id int UNIQUE,
          CONSTRAINT PK_generate PRIMARY KEY (generated_workout_id)
          );

          CREATE TABLE generated_workouts (
          randomized_id SERIAL,
          generated_workout_id int,
          workout_id SERIAL,
          difficulty varchar(100) NOT NULL,
          user_id SERIAL,
          CONSTRAINT PK_randomized_id PRIMARY KEY (randomized_id),
          CONSTRAINT FK_generated_workout_id FOREIGN KEY (generated_workout_id) REFERENCES generate_new_workout_id(generated_workout_id),
          CONSTRAINT FK_workout_id FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
          CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
          );



COMMIT TRANSACTION;
