package org.example.CowsAndBulls.dao;


import org.example.CowsAndBulls.TestApplicationConfiguration;
import org.example.CowsAndBulls.model.Games;
import org.example.CowsAndBulls.model.Rounds;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.security.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)

public class RoundsDatabaseDaoTest {
    @Autowired
    GamesDao gameDao;
    @Autowired
    RoundsDao roundDao;
    @Before
    public void setUp() throws Exception {
        List<Rounds> rounds = roundDao.getAllRounds();
        for(Rounds r: rounds){
            roundDao.deleteByGameId((r.getGameId()));
        }
        List<Games> games = gameDao.getAllGames();
        for(Games g: games){
            gameDao.deleteByGameId((g.getGameId()));
        }
    }

    @Test
    public void testAddRoundById() {
        Games game = new Games();
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);

        Rounds round = new Rounds();
        round.setGameId(game.getGameId());
        round.setRoundId(1);
        round.setGuess("2345");
        round.setResult("e:0:p:0");
        round.setGameId(game.getGameId());
        round = roundDao.addRound(round);
        List<Rounds> rounds = roundDao.getAllRounds();
        assertEquals(1, rounds.size());
    }

    @Test
    public void testGetAllRounds() {
        Games game = new Games();
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);
        Rounds round = new Rounds();
        round.setGuess("2345");
        round.setResult("e:0:p:0");
        round.setGameId(game.getGameId());
        roundDao.addRound(round);
        Rounds round2 = new Rounds();
        round2.setGuess("2394");
        round2.setResult("e:4:p:0");
        round2.setGameId(game.getGameId());
        roundDao.addRound(round2);
        List<Rounds> rounds = roundDao.getAllRounds();
        assertEquals(2, rounds.size());
    }
}