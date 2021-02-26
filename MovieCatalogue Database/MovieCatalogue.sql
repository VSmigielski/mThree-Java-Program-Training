-- Delete database if it exists, start from scratch each time 
-- Good for new development
DROP DATABASE IF EXISTS MovieCatalogue;

-- Create database
CREATE DATABASE MovieCatalogue;

-- Use the correct database for the table creation
USE MovieCatalogue;

-- Create table Genre
CREATE TABLE `Genre` (
	`GenreID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `GenreName` VARCHAR(30) NOT NULL
);

-- Create table Director
CREATE TABLE `Director` (
	`DirectorID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `FirstName` VARCHAR(30) NOT NULL,
    `LastName` VARCHAR(30) NOT NULL,
    `BirthDate` DATE NULL
);

-- Create table Rating
CREATE TABLE `Rating` (
	`RatingID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `RatingName` VARCHAR(5) NOT NULL
);

-- Create table Actor
CREATE TABLE `Actor` (
	`ActorID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `FirstName` VARCHAR(30) NOT NULL,
    `LastName` VARCHAR(30) NOT NULL,
    `BirthDate` DATE NULL
);

-- Create table Movie
CREATE TABLE `Movie` (
	`MovieID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `GenreID` INT NOT NULL,
    `DirectorID` INT NULL,
    `RatingID` INT NULL,
    `Title` VARCHAR(128) NOT NULL,
    `ReleaseDate` DATE NULL,
	FOREIGN KEY `fk_MovieGenre` (`GenreID`) 
		REFERENCES `Genre`(`GenreID`),
	FOREIGN KEY `fk_MovieDirector` (`DirectorID`) 
		REFERENCES `Director`(`DirectorID`),
	FOREIGN KEY `fk_MovieRating`(`RatingID`) 
		REFERENCES `Rating`(`RatingID`)
);

CREATE TABLE `CastMembers` (
	`CastMemberID` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `ActorID` INT NOT NULL, 
    `MovieID` INT NOT NULL,
    `Role` VARCHAR(50) NOT NULL,
    FOREIGN KEY `fk_CastMemberActor`(`ActorID`) 
		REFERENCES `Actor`(`ActorID`),
	FOREIGN KEY `fk_CastMemberMovie`(`MovieID`) 
		REFERENCES `Movie`(`MovieID`)
);