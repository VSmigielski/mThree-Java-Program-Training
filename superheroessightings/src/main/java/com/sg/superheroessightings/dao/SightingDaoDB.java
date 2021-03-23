package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SightingDaoDB implements SightingDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sighting WHERE sightingId = ?";
            return jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sighting";
        return jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    public List<Sighting> getAllSightingsGivenDate(String sightingDate) {
        try {
            final String SELECT_ALL_SIGHTINGS = "SELECT * "
                    + "FROM sighting "
                    + "JOIN location on sighting.locationId = location.locationId "
                    + "JOIN superHumans on sighting.superHumanId = superHumans.superHumanId "
                    + "WHERE sighting.sightingDate = ?";
            return jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper(), sightingDate);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTINGS = "INSERT INTO sighting(superHumanId, locationId, sightingDate) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTINGS,
                sighting.getSuperHumanId(),
                sighting.getLocationId(),
                sighting.getSightingDate());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTINGS = "UPDATE sighting SET superHumanId = ?, locationId = ?, sightingDate = ? "
                + "WHERE sightingId = ?";
        jdbc.update(UPDATE_SIGHTINGS,
                sighting.getSuperHumanId(),
                sighting.getLocationId(),
                sighting.getSightingDate(),
                sighting.getSightingId());
    }

    @Override
    public List<Sighting> getMostRecentSightings() {
            final String SELECT_SIGHTINGS_BY_DATE = "SELECT * FROM sighting ORDER BY sightingDate DESC LIMIT 10";
            return jdbc.query(SELECT_SIGHTINGS_BY_DATE, new SightingMapper());
    }

    @Override
    public void deleteSightingById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE sightingId = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setLocationId(rs.getInt("locationId"));
            sighting.setSuperHumanId(rs.getInt("superHumanId"));
            sighting.setSightingDate(rs.getString("sightingDate"));

            return sighting;
        }
    }
}
