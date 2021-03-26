package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Organizations;
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
            SuperHumans superHuman = jdbc.queryForObject(SELECT_SUPERHUMAN_BY_ID, new SuperHumansMapper(), id);
            superHuman.setPowers(getPowerForSuperhuman(id));
            superHuman.setOrganizations(getOrganizationsForSuperhuman(id));
            return superHuman;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private void insertSuperAndOrg(SuperHumans superHumans) {
        final String INSERT_SUPERHUMANORG = "INSERT INTO superHumansOrganization(superHumanId, organizationId) "
                + "VALUES(?,?)";
        for(Organizations organization : superHumans.getOrganizations()) {
            jdbc.update(INSERT_SUPERHUMANORG, superHumans.getSuperHumanId(), organization.getOrganizationId());
        }
    }

    private Powers getPowerForSuperhuman(int id) {
        final String SELECT_POWER_FOR_SUPER = "SELECT p.* FROM powers p JOIN superHumans s ON s.powerId = p.powerId WHERE s.superHumanId = ?";
        return jdbc.queryForObject(SELECT_POWER_FOR_SUPER, new PowersDaoDB.PowersMapper(), id);
    }

    private List<Organizations> getOrganizationsForSuperhuman(int id) {
        final String SELECT_ORGS_FOR_SUPER = "SELECT o.* FROM organizations o JOIN superHumansOrganization os ON o.organizationId = os.organizationId WHERE os.superHumanId = ?";
        return jdbc.query(SELECT_ORGS_FOR_SUPER, new OrganizationsDaoDB.OrganizationsMapper(), id);
    }

    private void associatePowersAndOrganizations(List<SuperHumans> superHumans) {
        for (SuperHumans superHuman : superHumans) {
            superHuman.setPowers(getPowerForSuperhuman(superHuman.getSuperHumanId()));
            superHuman.setOrganizations(getOrganizationsForSuperhuman(superHuman.getSuperHumanId()));
        }

    }

    public List<SuperHumans> getSuperHumansForPower(Powers powers) {
        final String SELECT_SUPER_FOR_ORG = "SELECT * FROM superHumans WHERE powerId = ?";
        List<SuperHumans> superHumans = jdbc.query(SELECT_SUPER_FOR_ORG, new SuperHumansMapper(), powers.getPowerId());
        associatePowersAndOrganizations(superHumans);
        return superHumans;
    }

    @Override
    public List<SuperHumans> getAllSuperHumans() {
        final String SELECT_ALL_SUPERHUMANS = "SELECT * FROM superHumans";
        List<SuperHumans> superHumans = jdbc.query(SELECT_ALL_SUPERHUMANS, new SuperHumansMapper());
        associatePowersAndOrganizations(superHumans);
        return superHumans;
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
        final String INSERT_SUPERHUMAN = "INSERT INTO superHumans(superHumanName, superDescription, isEvil, powerId) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_SUPERHUMAN,
                superHuman.getName(),
                superHuman.getDescription(),
                superHuman.isEvil(),
                superHuman.getPowers().getPowerId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superHuman.setSuperHumanId(newId);
        insertSuperAndOrg(superHuman);
        return superHuman;
    }

    @Override
    public void updateSuperHuman(SuperHumans superHuman) {
        final String UPDATE_SUPERHUMAN = "UPDATE superHumans SET superHumanName = ?, superDescription = ?, isEvil = ?, powerId = ? "
                + "WHERE superHumanId = ?";
        jdbc.update(UPDATE_SUPERHUMAN,
                superHuman.getName(),
                superHuman.getDescription(),
                superHuman.isEvil(),
                superHuman.getPowers().getPowerId(),
                superHuman.getSuperHumanId());

        final String DELETE_SUPERHUMAN_ORGANIZATION = "DELETE FROM superHumansOrganization WHERE superHumanId = ?";
        jdbc.update(DELETE_SUPERHUMAN_ORGANIZATION, superHuman.getSuperHumanId());
        insertSuperAndOrg(superHuman);
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



    public static final class SuperHumansMapper implements RowMapper<SuperHumans> {

        @Override
        public SuperHumans mapRow(ResultSet rs, int index) throws SQLException {
            SuperHumans superhumans = new SuperHumans();
            superhumans.setSuperHumanId(rs.getInt("superHumanId"));
            superhumans.setName(rs.getString("superHumanName"));
            superhumans.setDescription(rs.getString("superDescription"));
            superhumans.setEvil(rs.getBoolean("isEvil"));

            return superhumans;
        }
    }
}
