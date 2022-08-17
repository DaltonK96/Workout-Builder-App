BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO workouts (workout_name,workout_desc,expected_target) VALUES ('workout1','workout desc','arms'),
('workout2','workout desc','legs'),
('workout3','workout desc','back'),
('workout4','workout desc','cardio'),
('workout5','workout desc','total body'),
('workout6','description', 'arms'),
('workout7', 'descp', 'arms');

INSERT INTO beginner_workouts (workout_id, weight, expected_rep_set, expected_time, difficulty)
VALUES (1,'20lbs','reps',3, 'beginner'),
(2,'25lbs','reps4',7, 'beginner'),
(3,'7lbs','reps3',10, 'beginner'),
(4,'20lbs','re2ps',2, 'beginner'),
(5,'2lbs','rep2s',8, 'beginner'),
(6, '5lb', 'reps', 2, 'beginner'),
(7, '18lbs', 'reprep', 5, 'beginner');

INSERT INTO intermediate_workouts (workout_id, weight, expected_rep_set, expected_time, difficulty)
VALUES (1,'60lbs','reps',3, 'intermediate'),
(2,'55lbs','reps4',7, 'intermediate'),
(3,'71lbs','reps3',10, 'intermediate'),
(4,'90lbs','re2ps',2, 'intermediate'),
(5,'100bs','rep2s',8, 'intermediate');

INSERT INTO extreme_workouts (workout_id, weight, expected_rep_set, expected_time, difficulty)
VALUES (1,'80lbs','reps',3, 'extreme'),
(2,'551lbs','reps4',7, 'extreme'),
(3,'713lbs','reps3',10, 'extreme'),
(4,'904lbs','re2ps',2, 'extreme'),
(5,'1005bs','rep2s',8, 'extreme');

COMMIT TRANSACTION;
