package org.example.CowsAndBulls.dao;

import org.example.CowsAndBulls.model.Rounds;
import org.springframework.stereotype.Component;

import java.util.List;


public interface RoundsDao {
    List<Rounds> getAllRoundsByGameId(int gameId);
    List<Rounds> getAllRounds();
    Rounds getRoundById(int roundId);
    Rounds addRound(Rounds round);
    boolean deleteByGameId(int gameId);
}
