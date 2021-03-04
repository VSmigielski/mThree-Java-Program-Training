package org.example.DVDDatabase.data;

import org.example.DVDDatabase.models.DVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
    @Profile("database")
    public class DVDDatabaseDao implements DVDDao {
        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public DVDDatabaseDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public DVD add(DVD dvd) {

            final String sql = "INSERT INTO dvd(dvd, note) VALUES(?,?);";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update((Connection conn) -> {

                PreparedStatement statement = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, dvd.getDVD());
                statement.setString(2, dvd.getNote());
                return statement;

            }, keyHolder);

            dvd.setId(keyHolder.getKey().intValue());

            return dvd;
        }

        @Override
        public List<DVD> getAll() {
            final String sql = "SELECT id, dvd, note, finished FROM dvd;";
            return jdbcTemplate.query(sql, new DVDMapper());
        }

        @Override
        public DVD findById(int id) {

            final String sql = "SELECT id, dvd, note, finished "
                    + "FROM dvd WHERE id = ?;";

            return jdbcTemplate.queryForObject(sql, new DVDMapper(), id);
        }

        @Override
        public boolean update(DVD dvd) {

            final String sql = "UPDATE dvd SET "
                    + "dvd = ?, "
                    + "note = ?, "
                    + "finished = ? "
                    + "WHERE id = ?;";

            return jdbcTemplate.update(sql,
                    dvd.getDVD(),
                    dvd.getNote(),
                    dvd.isFinished(),
                    dvd.getId()) > 0;
        }

        @Override
        public boolean deleteById(int id) {
            final String sql = "DELETE FROM dvd WHERE id = ?;";
            return jdbcTemplate.update(sql, id) > 0;
        }

        private static final class DVDMapper implements RowMapper<DVD> {

            @Override
            public DVD mapRow(ResultSet rs, int index) throws SQLException {
                DVD td = new DVD();
                td.setId(rs.getInt("id"));
                td.setDVD(rs.getString("dvd"));
                td.setNote(rs.getString("note"));
                td.setFinished(rs.getBoolean("finished"));
                return td;
            }
        }
    }


