package org.example.CowsAndBulls.dao;

import org.example.CowsAndBulls.TestApplicationConfiguration;
import org.example.CowsAndBulls.model.Games;
import org.example.CowsAndBulls.model.Rounds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class RoundsDatabaseDaoTest {

    @Autowired
    GamesDao gameDao;

    @Autowired
    RoundsDao roundDao;

    @BeforeEach
    void setUp() {
        List<Games> games = gameDao.getAllGames();
        for (Games game : games) {
            gameDao.deleteByGameId(game.getGameId());
        }
        List<Rounds> rounds = roundDao.getAllRounds();
        for (Rounds round : rounds) {
            roundDao.deleteByGameId(round.getGameId());
        }
    }

    @Test
    void getAllRounds() {
        Games game = new Games();
        game.setGameId(1);
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);

        Rounds round = new Rounds();
        round.setGameId(1);
        round.setGuess("1234");
        round.setResult("e:0:p:0");
        roundDao.addRound(round);

        Rounds round2 = new Rounds();
        round.setGameId(1);
        round2.setGuess("5678");
        round2.setResult("e:4:p:0");
        roundDao.addRound(round2);

        List<Rounds> rounds = roundDao.getAllRounds();

        assertEquals(2, rounds.size());
    }

    @Test
    void getAllRoundsByGameId() {
    }

    @Test
    void getRoundById() {
    }

    @Test
    void addRound() {

    }

    @Test
    void deleteByGameId() {
    }
}