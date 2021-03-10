
DROP DATABASE IF EXISTS CowsAndBullsDBtest;
CREATE DATABASE CowsAndBullsDBtest;
USE CowsAndBullsDBtest;

CREATE TABLE game (
	gameId INT PRIMARY KEY AUTO_INCREMENT,
    answer char(4),
    finished BOOLEAN DEFAULT false
    );
    
    
CREATE TABLE round (
	roundId INT PRIMARY KEY AUTO_INCREMENT,
    gameId INT NOT NULL,
    guessTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guess CHAR(4),
    result CHAR(7),
    FOREIGN KEY fk_gameid (gameId) REFERENCES game(gameId)
    );
