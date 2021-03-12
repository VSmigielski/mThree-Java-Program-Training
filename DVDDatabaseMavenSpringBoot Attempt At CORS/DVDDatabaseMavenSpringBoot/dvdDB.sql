DROP DATABASE IF EXISTS dvdDB;
CREATE DATABASE dvdDB;


USE dvdDB;

CREATE TABLE dvd(
dvdId INT PRIMARY KEY,
Title VARCHAR(40) NOT NULL,
releaseYear SMALLINT NOT NULL,
director VARCHAR(40) NOT NULL,
rating VARCHAR(5) NOT NULL,
notes VARCHAR(256) NOT NULL);



INSERT INTO dvd(dvdId, title, releaseYear, director, rating, notes)
VALUES(0, 'A Great Tale', 2015, 'Sam Jones', 'PG', 'This really is a great tale!'),
(1, 'Just A Tale', 2015, 'Joe Baker', 'PG', 'It is a tale!'),
(2, 'A Super Tale', 1985, 'Joe Smith', 'PG',  'The original!'),
(42, 'A New Tale', 2016, 'Jack Jameson', 'PG-13','I am just changing my note…');


