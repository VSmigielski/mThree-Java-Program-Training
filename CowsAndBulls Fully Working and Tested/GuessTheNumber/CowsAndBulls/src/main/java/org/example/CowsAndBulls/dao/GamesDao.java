package org.example.CowsAndBulls.dao;

import org.example.CowsAndBulls.model.Games;
import org.springframework.stereotype.Component;

import java.util.List;


public interface GamesDao {
    List<Games> getAllGames();
    Games getGameById(int gameId);
    Games addGame(Games game);
    void updateGame(Games round);
    boolean deleteByGameId(int gameId);
    boolean manualUpdateGame(Games game);
}
