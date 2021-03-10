package org.example.CowsAndBulls.dao;

import org.example.CowsAndBulls.TestApplicationConfiguration;
import org.example.CowsAndBulls.model.Games;
import org.example.CowsAndBulls.model.Rounds;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GamesDatabaseDaoTest {

    @Autowired
    GamesDao gameDao;
    @Autowired
    RoundsDao roundDao;

    @Before
    public void setUp() throws Exception {
        List<Games> games = gameDao.getAllGames();
        for (Games game: games) {
            roundDao.deleteByGameId(game.getGameId());
            gameDao.deleteByGameId((game.getGameId()));
        }
    }

    @Test
    public void getAllGames() {
        Games game = new Games();
        game.setAnswer("1342");
        game.setFinished(false);
        gameDao.addGame(game);
        Games game2 = new Games();
        game2.setAnswer("1823");
        game2.setFinished(true);
        gameDao.addGame(game2);
        List<Games> games = gameDao.getAllGames();
        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    @Test
    public void addGame() {
        Games game = new Games();
        game.setAnswer("1234");
        game.setFinished(false);
        game = gameDao.addGame(game);
        Games fromDao = gameDao.getGameById(game.getGameId());
        assertEquals(game, fromDao);
    }

    @Test
    public void updateGame() {
        Games game = new Games();
        game.setAnswer("2784");
        game.setFinished(false);
        game = gameDao.addGame(game);
        Games fromDao = gameDao.getGameById(game.getGameId());
        assertEquals(game, fromDao);
        game.setFinished(true);
        gameDao.updateGame(game);
        assertNotEquals(game, fromDao);
        fromDao = gameDao.getGameById((game.getGameId()));
        assertEquals(game, fromDao);
    }

}