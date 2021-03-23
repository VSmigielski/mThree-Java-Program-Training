package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Powers;
import com.sg.superheroessightings.entities.SuperHumans;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuperHumansDaoDB implements SuperHumansDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public SuperHumans getSuperHumanById(int id) {
        try {
            final String SELECT_SUPERHUMAN_BY_ID = "SELECT * FROM superHumans WHERE superHumanId = ?";
            return jdbc.queryForObject(SELECT_SUPERHUMAN_BY_ID, new SuperHumansMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperHumans> getAllSuperHumans() {
        final String SELECT_ALL_SUPERHUMANS = "SELECT * FROM superHumans";
        return jdbc.query(SELECT_ALL_SUPERHUMANS, new SuperHumansMapper());
    }

    @Override
    public List<SuperHumans> getAllSuperHumansGivenLocation(int locationId) {
        final String SELECT_ALL_SUPERHUMANS = "SELECT * "
                + "FROM superHumans "
                + "JOIN sighting on superHumans.superHumanId = sighting.superHumanId "
                + "JOIN location on sighting.locationId = location.locationId "
                + "WHERE location.locationId = ?";
        return jdbc.query(SELECT_ALL_SUPERHUMANS, new SuperHumansMapper(), locationId);
    }

    @Override
    public List<SuperHumans> getAllSuperHumansGivenOrganization(int organizationId) {
        final String SELECT_ALL_SUPERHUMANS = "SELECT * "
                + "FROM superHumans "
                + "JOIN superHumansOrganization on superHumans.superHumanId = superHumansOrganization.superHumanId "
                + "JOIN organizations on superHumansOrganization.organizationId = organizations.organizationId "
                + "WHERE organizations.organizationId = ?";
        return jdbc.query(SELECT_ALL_SUPERHUMANS, new SuperHumansMapper(), organizationId);
    }

    @Override
    @Transactional
    public SuperHumans addSuperHuman(SuperHumans superHuman) {
        final String INSERT_SUPERHUMAN = "INSERT INTO superHumans(superHumanName, superDescription, powerId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SUPERHUMAN,
                superHuman.getName(),
                superHuman.getDescription(),
                superHuman.getPowerId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superHuman.setSuperHumanId(newId);
        return superHuman;
    }

    @Override
    public void updateSuperHuman(SuperHumans superHuman) {
        final String UPDATE_SUPERHUMAN = "UPDATE superHumans SET superHumanName = ?, superDescription = ?, powerId = ? "
                + "WHERE superHumanId = ?";
        jdbc.update(UPDATE_SUPERHUMAN,
                superHuman.getName(),
                superHuman.getDescription(),
                superHuman.getPowerId(),
                superHuman.getSuperHumanId());
    }

    @Override
    @Transactional
    public void deleteSuperHumanById(int id) {
        final String DELETE_SUPERHUMAN_SIGHTING = "DELETE FROM sighting WHERE superHumanId = ?";
        jdbc.update(DELETE_SUPERHUMAN_SIGHTING, id);

        final String DELETE_SUPERHUMAN_ORG = "DELETE FROM superHumansOrganization WHERE shoId = ?";
        jdbc.update(DELETE_SUPERHUMAN_ORG, id);

        final String DELETE_SUPERHUMAN = "DELETE FROM superHumans WHERE superHumanId = ?";
        jdbc.update(DELETE_SUPERHUMAN, id);
    }

    @Override
    public List<Powers> getPowersForSuperHuman() {
        final String SELECT_ALL_POWERS = "SELECT * FROM powers";
        return jdbc.query(SELECT_ALL_POWERS, new PowersDaoDB.PowersMapper());
    }

    public static final class SuperHumansMapper implements RowMapper<SuperHumans> {

        @Override
        public SuperHumans mapRow(ResultSet rs, int index) throws SQLException {
            SuperHumans superhumans = new SuperHumans();
            superhumans.setSuperHumanId(rs.getInt("superHumanId"));
            superhumans.setName(rs.getString("superHumanName"));
            superhumans.setDescription(rs.getString("superDescription"));
            superhumans.setPowerId(rs.getInt("powerId"));

            return superhumans;
        }
    }
}
