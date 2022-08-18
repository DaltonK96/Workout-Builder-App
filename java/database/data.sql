BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO workouts (workout_name,workout_desc,expected_target) VALUES ('Curls','bend your arm towards your body at the elbow','arms'),
('Squats','lower your hips from a standing position and then stand back up','legs'),
('DeadLift','movement in which your hips hinge backward to lower down and pick up a weighted barbell or kettlebell from the floor','back'),
('Running','Pretend someone is chasing you and GO','cardio'),
('Crunches','strengthen the abdomen by contracting it to flex your spine and pull your torso off the floor','abs');

INSERT INTO beginner_workouts (workout_id, weight, expected_rep_set, expected_time, difficulty)
VALUES (1,'15lbs','5 reps/3 sets',5, 'beginner'),
(2,'','15 reps/3 sets',5, 'beginner'),
(3,'30lbs','5 reps/3 sets',5, 'beginner'),
(4,'','',10, 'beginner'),
(5,'','15 reps/3 sets',5, 'beginner');

INSERT INTO intermediate_workouts (workout_id, weight, expected_rep_set, expected_time, difficulty)
VALUES (1,'30lbs','5 reps/3 sets',5, 'intermediate'),
(2,'75lbs','5 reps/3 sets',5, 'intermediate'),
(3,'75lbs','5 reps/3 sets',5, 'intermediate'),
(4,'','',20, 'intermediate'),
(5,'','25 reps/3 sets',5, 'intermediate');

INSERT INTO extreme_workouts (workout_id, weight, expected_rep_set, expected_time, difficulty)
VALUES (1,'150lbs','15 reps/30 sets',5, 'extreme'),
(2,'450lbs','2 reps/3 sets',7, 'extreme'),
(3,'1000lbs','1 rep/1 set',1, 'extreme'),
(4,'','',60, 'extreme'),
(5,'','50 reps/5 sets',8, 'extreme');

COMMIT TRANSACTION;
