package org.example.CowsAndBulls.dao;

import org.example.CowsAndBulls.model.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;


@Repository
@Profile("database")
public class GamesDatabaseDao implements GamesDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Games> getAllGames() {
        final String sql = "SELECT gameId, answer, finished FROM game;";
        return jdbc.query(sql, new GameMapper());
    }

    @Override
    public Games getGameById(int gameId) {
        try {
            final String sql = "SELECT * FROM game WHERE gameId = ?;";
            return jdbc.queryForObject(sql, new GameMapper(), gameId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Games addGame(Games game) {
        final String INSERT_GAME = "INSERT INTO game(answer, finished) VALUES(?,?)";
        jdbc.update(INSERT_GAME,
                game.getAnswer(),
                game.isFinished());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        return game;
    }

    @Override
    public void updateGame(Games game) {
        final String UPDATE_GAME = "UPDATE game SET finished = ? WHERE gameId = ?;";
        jdbc.update(UPDATE_GAME, game.isFinished(), game.getGameId());
    }

    public boolean deleteByGameId(int gameID) {
        final String sql = "DELETE FROM game WHERE gameId = ?;";
        return jdbc.update(sql, gameID) > 0;
    }

    @Override
    public boolean manualUpdateGame(Games game) {
        final String sql = "UPDATE game SET "
                + "gameId = ?, "
                + "answer = ?, "
                + "finished = ? "
                + "WHERE gameId = ?;";
        return jdbc.update(sql,
                game.getGameId(),
                game.getAnswer(),
                game.isFinished(),
                game.getGameId()
        ) > 0;
    }



    public static final class GameMapper implements RowMapper<Games> {

        @Override
        public Games mapRow(ResultSet rs, int index) throws SQLException {
            Games game = new Games();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }

}