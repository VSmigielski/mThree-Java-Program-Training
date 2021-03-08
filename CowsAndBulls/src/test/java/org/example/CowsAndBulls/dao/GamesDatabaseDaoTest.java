package org.example.CowsAndBulls.dao;

import org.example.CowsAndBulls.TestApplicationConfiguration;
import org.example.CowsAndBulls.config.GamesController;
import org.example.CowsAndBulls.model.Games;
import java.util.List;

import org.example.CowsAndBulls.model.Rounds;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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

public class GamesDatabaseDaoTest {

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


    public GamesDatabaseDaoTest() {
    }

    @Before
    public void setUp() throws Exception {
        List<Games> games = gameDao.getAllGames();
        for (Games game : games) {
            gameDao.deleteByGameId(game.getGameId());
        }
        List<Rounds> rounds = roundDao.getAllRounds();
        for (Rounds round : rounds) {
            roundDao.deleteByGameId(round.getRoundId());
        }
    }

    // Works
    @Test
    public void testAddGetGame() {
        Games game = new Games();
        game.setAnswer("1234");
        game = gameDao.addGame(game);

        Games fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);
    }

    // retrieves 0 not 2
    @Test
    public void testGetAllGames() {
        Games game = new Games();
        game.setAnswer("1234");
        gameDao.addGame(game);

        Games game2 = new Games();
        game2.setAnswer("5678");
        gameDao.addGame(game2);

        List<Games> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    // Doesn't work : Null pointer line 103
    @Test
    public void testUpdateGame() {
        Games game = new Games();
        game.setGameId(3);
        game.setAnswer("2345");
        game.setFinished(false);
        game = gameDao.addGame(game);

        Games fromDao = gameDao.getGameById(3);

        assertEquals(game, fromDao);

        game.setGameId(3);
        game.setAnswer("2345");
        game.setFinished(true);

        gameDao.updateGame(game);

        assertNotEquals(game, fromDao);

        fromDao = gameDao.getGameById(2);

        assertEquals(game, fromDao);
    }
}