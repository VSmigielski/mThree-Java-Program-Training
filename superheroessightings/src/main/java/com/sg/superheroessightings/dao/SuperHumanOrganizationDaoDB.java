package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.SuperHumanOrganization;
import com.sg.superheroessightings.entities.SuperHumans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuperHumanOrganizationDaoDB implements SuperHumanOrganizationDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public SuperHumanOrganization getSuperHumanOrganizationById(int shoId) {
        try {
            final String SELECT_SUPERHUMANORG_BY_ID = "SELECT * FROM superHumansOrganization WHERE shoId = ?";
            return jdbc.queryForObject(SELECT_SUPERHUMANORG_BY_ID, new SuperHumanOrganizationMapper(), shoId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperHumanOrganization> getAllSuperHumanOrganization() {
        final String SELECT_ALL_SUPERHUMANORG = "SELECT * FROM superHumansOrganization";
        return jdbc.query(SELECT_ALL_SUPERHUMANORG, new SuperHumanOrganizationMapper());
    }

    @Override
    public SuperHumanOrganization addSuperHumanOrganization(SuperHumanOrganization superHumanOrganization) {
        final String INSERT_SUPERHUMANORG = "INSERT INTO superHumansOrganization(superHumanId, organizationId) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SUPERHUMANORG,
                superHumanOrganization.getSuperHumanId(),
                superHumanOrganization.getOrganizationId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superHumanOrganization.setShoId(newId);
        return superHumanOrganization;
    }

    @Override
    public void updateSuperHumanOrganization(SuperHumanOrganization superHumanOrganization) {
        final String UPDATE_SUPERHUMANORG = "UPDATE superHumansOrganization SET superHumanId = ?, organizationId = ? "
                + "WHERE shoId = ?";
        jdbc.update(UPDATE_SUPERHUMANORG,
                superHumanOrganization.getSuperHumanId(),
                superHumanOrganization.getOrganizationId(),
                superHumanOrganization.getShoId());
    }

    @Override
    public void deleteSuperHumanOrganizationById(int shoId) {
        final String DELETE_SUPERHUMAN_ORGANIZATION = "DELETE FROM superHumansOrganization WHERE shoId = ?";
        jdbc.update(DELETE_SUPERHUMAN_ORGANIZATION, shoId);
    }

    public static final class SuperHumanOrganizationMapper implements RowMapper<SuperHumanOrganization> {

        @Override
        public SuperHumanOrganization mapRow(ResultSet rs, int index) throws SQLException {
            SuperHumanOrganization superHumanOrganization = new SuperHumanOrganization();
            superHumanOrganization.setShoId(rs.getInt("shoId"));
            superHumanOrganization.setSuperHumanId(rs.getInt("superHumanId"));
            superHumanOrganization.setOrganizationId(rs.getInt("organizationId"));

            return superHumanOrganization;
        }
    }

}
