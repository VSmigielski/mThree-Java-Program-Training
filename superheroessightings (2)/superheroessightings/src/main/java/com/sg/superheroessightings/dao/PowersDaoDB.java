package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Powers;
import com.sg.superheroessightings.entities.SuperHumans;
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
public class PowersDaoDB implements PowersDao {
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    SuperHumansDao superHumansDao;

    @Override
    public Powers getPowerById(int id) {
        try {
            final String SELECT_POWER_BY_ID = "SELECT * FROM powers WHERE powerId = ?";
            return jdbc.queryForObject(SELECT_POWER_BY_ID, new PowersMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Powers> getAllPowers() {
        final String SELECT_ALL_POWERS = "SELECT * FROM powers";
        return jdbc.query(SELECT_ALL_POWERS, new PowersMapper());
    }

    @Override
    @Transactional
    public Powers addPower(Powers powers) {
        final String INSERT_POWER = "INSERT INTO powers(superpower) "
                + "VALUES(?)";
        jdbc.update(INSERT_POWER,
                powers.getSuperpower());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        powers.setPowerId(newId);
        return powers;
    }

    @Override
    public void updatePower(Powers powers) {
        final String UPDATE_POWER = "UPDATE powers SET superpower = ? "
                + "WHERE powerId = ?";
        jdbc.update(UPDATE_POWER,
                powers.getSuperpower(),
                powers.getPowerId());
    }

    @Override
    @Transactional
    public void deletePowerById(int id) {
        List<SuperHumans> superHumans = superHumansDao.getSuperHumansForPower(getPowerById(id));
        for(SuperHumans superHuman : superHumans) {
            final String DELETE_ORGANIZATION_SUPER = "DELETE os.* FROM superHumansOrganization os " +
                    "JOIN superHumans sh ON sh.superHumanId = os.superHumanId WHERE os.superHumanId = ? AND sh.powerId = ?";
            jdbc.update(DELETE_ORGANIZATION_SUPER, superHuman.getSuperHumanId(), id);
            final String DELETE_SIGHTING_SUPER = "DELETE s.* FROM sighting s " +
                    "JOIN superHumans sh ON sh.superHumanId = s.superHumanId WHERE s.superHumanId = ? AND sh.powerId = ?";
            jdbc.update(DELETE_SIGHTING_SUPER, superHuman.getSuperHumanId(), id);
            final String DELETE_SUPER = "DELETE FROM superHumans WHERE superHumanId = ? AND powerId = ?";
            jdbc.update(DELETE_SUPER, superHuman.getSuperHumanId(), id);
        }

        final String DELETE_POWERS = "DELETE FROM powers WHERE powerId = ?";
        jdbc.update(DELETE_POWERS, id);
    }

    public static final class PowersMapper implements RowMapper<Powers> {

        @Override
        public Powers mapRow(ResultSet rs, int index) throws SQLException {
            Powers superPower = new Powers();
            superPower.setPowerId(rs.getInt("powerId"));
            superPower.setSuperpower(rs.getString("superpower"));

            return superPower;
        }
    }
}
