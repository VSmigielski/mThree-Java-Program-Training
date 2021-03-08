package org.example.CowsAndBulls.service;

import org.example.CowsAndBulls.dao.GamesDao;
import org.example.CowsAndBulls.dao.RoundsDao;
import org.example.CowsAndBulls.model.Games;
import org.example.CowsAndBulls.model.Rounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class GamesService {

    @Autowired
    GamesDao gameDao;

    @Autowired
    RoundsDao roundDao;

    public List<Games> getAllGames() {
        List<Games> games = gameDao.getAllGames();
        for (Games game : games) {
            if (!game.isFinished()) {
                game.setAnswer("****");
            }
        }
        return games;
    }

    public List<Rounds> getAllRoundsByGameId(int gameId) {
        return roundDao.getAllRoundsByGameId(gameId);
    }

    public Rounds makeGuess(Rounds round) {
        String answer = gameDao.getGameById(round.getGameId()).getAnswer();
        String guess = round.getGuess();
        String result = determineResult(guess, answer);
        round.setResult(result);

        if (guess.equals(answer)) {
            Games game = getGameById(round.getGameId());
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        return roundDao.addRound(round);
    }

    public Games getGameById(int gameId) {
        Games game = gameDao.getGameById(gameId);
        if (!game.isFinished()) {
            game.setAnswer("****");
        }

        return game;
    }

    public Games addGame(Games game) {
        return gameDao.addGame(game);
    }

    public int newGame() {
        Games game = new Games();
        game.setAnswer(generateAnswer());
        game = gameDao.addGame(game);

        return game.getGameId();
    }

    private String generateAnswer() {
        Random rnd = new Random();
        int digit1 = rnd.nextInt(10);

        int digit2 = rnd.nextInt(10);
        while (digit2 == digit1) {
            digit2 = rnd.nextInt(10);
        }

        int digit3 = rnd.nextInt(10);
        while (digit3 == digit2 || digit3 == digit1) {
            digit3 = rnd.nextInt(10);
        }

        int digit4 = rnd.nextInt(10);
        while (digit4 == digit3 || digit4 == digit2 || digit4 == digit1) {
            digit4 = rnd.nextInt(10);
        }

        String answer = String.valueOf(digit1) + String.valueOf(digit2)
                + String.valueOf(digit3) + String.valueOf(digit4);

        return answer;
    }

    public List<Rounds> getAllRounds() {
        List<Rounds> rounds = roundDao.getAllRounds();
        return rounds;
    }

    public String determineResult(String guess, String answer) {
        char[] guessChars = guess.toCharArray();
        char[] answerChars = answer.toCharArray();
        int exact = 0;
        int partial = 0;

        for (int i = 0; i < guessChars.length; i++) {
            // -1 indicates that index value of guessChars DNE in answer
            // otherwise the number must be in the string. Then check where
            if (answer.indexOf(guessChars[i]) > -1) {
                if (guessChars[i] == answerChars[i]) {
                    exact++;
                } else {
                    partial++;
                }
            }
        }

        String result = "e:" + exact + ":p:" + partial;

        return result;
    }

    public ResponseEntity deleteByGameId(int gameId) {
        if ( roundDao.deleteByGameId(gameId) && gameDao.deleteByGameId(gameId)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    public int getGameId(Games game) {
        return game.getGameId();
    }
    public boolean manualUpdateGame(Games game) {
        return gameDao.manualUpdateGame(game);
    }

}
