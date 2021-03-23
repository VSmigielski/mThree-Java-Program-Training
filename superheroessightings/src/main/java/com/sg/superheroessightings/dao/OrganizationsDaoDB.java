package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Organizations;
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
public class OrganizationsDaoDB implements OrganizationsDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organizations getOrganizationById(int id) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organizations WHERE organizationId = ?";
            return jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationsMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organizations> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organizations";
        return jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationsMapper());
    }

    @Override
    public List<Organizations> getAllOrganizationsGivenSuperHuman(int superHumanId) {
        try {
            final String SELECT_ALL_ORGANIZATIONS = "SELECT * "
                    + "FROM organizations "
                    + "JOIN superHumansOrganization on organizations.organizationId = superHumansOrganization.organizationId "
                    + "JOIN superHumans on superHumans.superHumanId = superHumansOrganization.superHumanId "
                    + "WHERE superHumans.superHumanId = ?";
            return jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationsMapper(), superHumanId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Organizations addOrganization(Organizations organizations) {
        final String INSERT_ORGANIZATION = "INSERT INTO organizations(organizationName, organizationDescription, address, contactInfo) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organizations.getOrganizationName(),
                organizations.getOrganizationDescription(),
                organizations.getAddress(),
                organizations.getContactInfo());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organizations.setOrganizationId(newId);
        return organizations;
    }

    @Override
    public void updateOrganization(Organizations organizations) {
        final String UPDATE_ORGANIZATION = "UPDATE organizations SET organizationName = ?, organizationDescription = ?, address = ?, contactInfo = ? "
                + "WHERE organizationId = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organizations.getOrganizationName(),
                organizations.getOrganizationDescription(),
                organizations.getAddress(),
                organizations.getContactInfo(),
                organizations.getOrganizationId());
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM superHumansOrganization WHERE organizationId = ?";
        jdbc.update(DELETE_SUPER_ORGANIZATION, id);

        final String DELETE_ORGANIZATION = "DELETE FROM organizations WHERE organizationId = ?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }

    public static final class OrganizationsMapper implements RowMapper<Organizations> {

        @Override
        public Organizations mapRow(ResultSet rs, int index) throws SQLException {
            Organizations organizations = new Organizations();
            organizations.setOrganizationId(rs.getInt("organizationId"));
            organizations.setOrganizationName(rs.getString("organizationName"));
            organizations.setOrganizationDescription(rs.getString("organizationDescription"));
            organizations.setAddress(rs.getString("address"));
            organizations.setContactInfo(rs.getString("contactInfo"));

            return organizations;
        }
    }
}
