package org.example.CowsAndBulls.dao;

import org.example.CowsAndBulls.model.Games;
import org.example.CowsAndBulls.model.Rounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


@Repository
@Profile("database")
public class RoundsDatabaseDao implements RoundsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Rounds> getAllRounds() {
        final String sql = "SELECT roundId, gameId, guessTime, guess, result FROM round;";
        return jdbc.query(sql, new RoundMapper());
    }

    @Override
    public List<Rounds> getAllRoundsByGameId(int gameId) {
        try {
            final String sql = "SELECT roundId, gameId, guessTime, guess, result " +
                    "FROM round " +
                    "WHERE gameId = ? " +
                    "ORDER BY guessTime;";
            List<Rounds> rounds = jdbc.query(sql, new RoundMapper(), gameId);
            return rounds;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Rounds getRoundById(int roundId) {
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundId = ?;";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Rounds addRound(Rounds round) {
        final String sql = "INSERT INTO round(gameId, guess, result) VALUES(?,?,?);";
        jdbc.update(sql, round.getGameId(), round.getGuess(), round.getResult());

        int newRoundId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newRoundId);
        return getRoundById(newRoundId);
    }


    @Override
    public boolean deleteByGameId(int gameId) {
        final String sql = "DELETE FROM round WHERE gameId = ?;";
        return jdbc.update(sql, gameId) > 0;
    }


    public static final class RoundMapper implements RowMapper<Rounds> {

        @Override
        public Rounds mapRow(ResultSet rs, int index) throws SQLException {
            Rounds round = new Rounds();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            round.setGuess(rs.getString("guess"));

            Timestamp timestamp = rs.getTimestamp("guessTime");
            round.setGuessTime(timestamp.toLocalDateTime());

            round.setResult(rs.getString("result"));
            return round;
        }
    }

}
