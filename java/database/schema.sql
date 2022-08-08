BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS workouts;

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
    weight varchar(20),
    expected_rep_set varchar(100) NOT NULL,
    expected_time int,
    expected_target varchar(100),
    CONSTRAINT valid_target CHECK(expected_target IN ('arms','legs','abs','back','cardio','total body')),
    CONSTRAINT PK_workout PRIMARY KEY (workout_id)
);

COMMIT TRANSACTION;
