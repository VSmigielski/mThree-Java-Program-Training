package org.example.CowsAndBulls.dao;

import org.example.CowsAndBulls.TestApplicationConfiguration;
import org.example.CowsAndBulls.config.GamesController;
import org.example.CowsAndBulls.model.Games;
import org.example.CowsAndBulls.model.Rounds;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.example.CowsAndBulls.service.GamesService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = org.example.CowsAndBulls.TestApplicationConfiguration.class)

public class RoundsDatabaseDaoTest {

    @Autowired
    @MockBean
    private GamesDao gameDao;

    @Autowired
    @MockBean
    private RoundsDao roundDao;

    @Autowired
    @MockBean
    private GamesService service;

    @Autowired
    @MockBean
    private GamesController gamesController;

    public RoundsDatabaseDaoTest() {
    }

    @Before
    public void setUp() throws Exception {
        List<Games> games = gameDao.getAllGames();
        for (Games game : games) {
            gameDao.deleteByGameId(game.getGameId());
        }
        List<Rounds> rounds = roundDao.getAllRounds();
        for (Rounds round : rounds) {
            roundDao.deleteByGameId(round.getGameId());
        }
    }

    // returns 0 not 2
    @Test
    public void testAddGetAll() {
        Games game = new Games();
        game.setGameId(1);
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);

        Rounds round = new Rounds();
        round.setRoundId(1);
        round.setGuess("1234");
        round.setResult("e:0:p:0");
        round.setGameId(1);
        roundDao.addRound(round);

        Rounds round2 = new Rounds();
        round.setRoundId(2);
        round2.setGuess("5678");
        round2.setResult("e:4:p:0");
        round2.setGameId(1);
        roundDao.addRound(round2);



        List<Rounds> rounds = roundDao.getAllRounds();

        assertEquals(2, rounds.size());
    }
}
