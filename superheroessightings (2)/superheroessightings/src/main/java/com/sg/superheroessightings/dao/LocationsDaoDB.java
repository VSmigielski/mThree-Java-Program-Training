package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Location;
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
public class LocationsDaoDB implements LocationDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int id) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE locationId = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM location";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getAllLocationsGivenSuperHumans(int superHumanId) {
        final String SELECT_ALL_LOCATIONS = "SELECT * "
                + "FROM location "
                + "JOIN sighting on location.locationId = sighting.locationId "
                + "JOIN superHumans on sighting.superHumanId = superHumans.superHumanId "
                + "WHERE superHumans.superHumanId = ?";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper(), superHumanId);
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location(locationName, locationDescription, address, latitude, longitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET locationName = ?, locationDescription = ?,  address = ?, latitude = ?, longitude = ? "
                + "WHERE locationId = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE locationId = ?";
        jdbc.update(DELETE_SIGHTING, id);

        final String DELETE_LOCATION = "DELETE FROM location WHERE locationId = ?";
        jdbc.update(DELETE_LOCATION, id);
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("locationId"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setAddress(rs.getString("address"));
            location.setLatitude(rs.getDouble("latitude"));
            location.setLongitude(rs.getDouble("longitude"));

            return location;
        }
    }
}
