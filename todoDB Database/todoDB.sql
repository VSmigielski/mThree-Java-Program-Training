DROP DATABASE IF EXISTS todoDB;
CREATE DATABASE todoDB;

USE todoDB;

CREATE TABLE todo(
id INT PRIMARY KEY AUTO_INCREMENT,
todo VARCHAR(40) NOT NULL,
note VARCHAR(255),
finished BOOLEAN DEFAULT false);


INSERT INTO todo(id, todo, note, finished)
VALUES(1, "Wash Car", "", false),
(2, "Laundry", "Jeans", false),
(3, "Wash dishes", "", true),
(4, "Play with Kitties", "Ball Roll", true),
(5, "Unclog drain", "", false),
(6, "Refrigerator Wipe", "Rotten food", false),
(7, "Clean litter", "", true);

