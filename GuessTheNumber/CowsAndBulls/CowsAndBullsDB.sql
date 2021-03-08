DROP DATABASE IF EXISTS CowsAndBullsDB;
CREATE DATABASE CowsAndBullsDB;
USE CowsAndBullsDB;

CREATE TABLE game (
	gameId INT PRIMARY KEY AUTO_INCREMENT,
    answer char(4),
    finished BOOLEAN DEFAULT false
    );
    
INSERT INTO game(gameId, answer, finished) VALUES
	(1, "1897", true),
	(2, "9368", true),
	(3, "4782", false);
    
CREATE TABLE round (
	roundId INT PRIMARY KEY AUTO_INCREMENT,
    gameId INT NOT NULL,
    guessTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guess CHAR(4),
    result CHAR(7),
    FOREIGN KEY fk_gameid (gameId) REFERENCES game(gameId)
    );
    INSERT INTO round (roundId, gameId, guessTime, guess, result) VALUES
    (1, 1, "2021-03-02 06:27:18", "2983", "e:0:p:1"),
    (2, 1, "2021-03-02 08:15:20", "9526", "e:0:p:1"),
    (3, 1, "2021-03-03 08:16:05", "1897", "e:4:p:0"),
	(4, 2, "2021-03-05 23:54:43", "1298", "e:1:p:1"),
    (5, 2, "2021-03-05 23:58:09", "9368", "e:4:p:0"),
    (6, 3, "2021-03-06 02:49:56", "4782", "e:4:p:0");