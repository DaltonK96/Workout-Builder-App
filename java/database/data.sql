BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO workouts (workout_name,workout_desc,expected_target) VALUES ('workout1','workout desc','arms'),
('workout2','workout desc','legs'),
('workout3','workout desc','back'),
('workout4','workout desc','cardio'),
('workout5','workout desc','total body');


COMMIT TRANSACTION;
