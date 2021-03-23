package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.TestApplicationConfiguration;
import com.sg.superheroessightings.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class OrganizationsDaoDBTest {

    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    PowersDao powersDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperHumansDao superhumansDao;

    @Autowired
    SuperHumanOrganizationDao superHumanOrganizationDao;

    @Before
    public void setUp() throws Exception {
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocationById(location.getLocationId());
        }

        List<Organizations> organization = organizationsDao.getAllOrganizations();
        for(Organizations organizations : organization) {
            organizationsDao.deleteOrganizationById(organizations.getOrganizationId());
        }

        List<Powers> powers = powersDao.getAllPowers();
        for(Powers power : powers) {
            powersDao.deletePowerById(power.getPowerId());
        }

        List<Sighting> sighting = sightingDao.getAllSightings();
        for(Sighting sightings : sighting) {
            sightingDao.deleteSightingById(sightings.getSightingId());
        }

        List<SuperHumanOrganization> superHumanOrganizations = superHumanOrganizationDao.getAllSuperHumanOrganization();
        for(SuperHumanOrganization superHumanOrganization : superHumanOrganizations) {
            superHumanOrganizationDao.deleteSuperHumanOrganizationById(superHumanOrganization.getOrganizationId());
        }

        List<SuperHumans> superhumans = superhumansDao.getAllSuperHumans();
        for(SuperHumans superhuman : superhumans) {
            superhumansDao.deleteSuperHumanById(superhuman.getSuperHumanId());
        }
    }

    @Test
    public void getOrganizationById() {
        Organizations organizations = new Organizations();
        organizations.setOrganizationName("Test organization");
        organizations.setOrganizationDescription("An testing organization");
        organizations.setAddress("123 Testing");
        organizations.setContactInfo("organizations@gmail.com");
        organizations.setOrganizationId(1);
        organizationsDao.addOrganization(organizations);

        Organizations fromDao = organizationsDao.getOrganizationById(organizations.getOrganizationId());

        assertEquals(organizations, fromDao);
    }

    @Test
    public void getAllOrganizations() {
        Organizations organizations = new Organizations();
        organizations.setOrganizationName("Test organization");
        organizations.setOrganizationDescription("An testing organization");
        organizations.setAddress("123 Testing");
        organizations.setContactInfo("organizations@gmail.com");
        organizationsDao.addOrganization(organizations);

        Organizations organizations2 = new Organizations();
        organizations2.setOrganizationName("#2 Test organization");
        organizations2.setOrganizationDescription("#2 An testing organization");
        organizations2.setAddress("321 Testing");
        organizations2.setContactInfo("organizations2@gmail.com");
        organizationsDao.addOrganization(organizations2);


        List<Organizations> organization = organizationsDao.getAllOrganizations();

        assertEquals(2, organization.size());
        assertTrue(organization.contains(organizations));
        assertTrue(organization.contains(organizations2));
    }

    @Test
    public void getAllOrganizationsGivenSuperHuman() {
        Organizations organizations = new Organizations();
        organizations.setOrganizationName("Test organization");
        organizations.setOrganizationDescription("An testing organization");
        organizations.setAddress("123 Testing");
        organizations.setContactInfo("organizations@gmail.com");
        organizationsDao.addOrganization(organizations);

        Organizations organizations2 = new Organizations();
        organizations2.setOrganizationName("#2 Test organization");
        organizations2.setOrganizationDescription("#2 An testing organization");
        organizations2.setAddress("321 Testing");
        organizations2.setContactInfo("organizations2@gmail.com");
        organizationsDao.addOrganization(organizations2);

        Organizations organizations3 = new Organizations();
        organizations3.setOrganizationName("#3 Test organization");
        organizations3.setOrganizationDescription("#3 An testing organization");
        organizations3.setAddress("321 Second Testing");
        organizations3.setContactInfo("organizations3@gmail.com");
        organizationsDao.addOrganization(organizations3);

        List<Organizations> organization = organizationsDao.getAllOrganizations();


        Powers power1 = new Powers();
        power1.setSuperpower("Mind-control");
        powersDao.addPower(power1);

        Powers power2 = new Powers();
        power2.setSuperpower("Strength");
        powersDao.addPower(power2);

        SuperHumans superhuman1 = new SuperHumans();
        superhuman1.setName("Test SuperHuman");
        superhuman1.setDescription("Test SuperHuman Description");
        superhuman1.setPowerId(power1.getPowerId());
        superhumansDao.addSuperHuman(superhuman1);

        SuperHumans superhuman2 = new SuperHumans();
        superhuman2.setName("Test SuperHuman");
        superhuman2.setDescription("Test SuperHuman Description");
        superhuman2.setPowerId(power2.getPowerId());
        superhumansDao.addSuperHuman(superhuman2);

        SuperHumanOrganization superHumanOrganization = new SuperHumanOrganization();
        superHumanOrganization.setSuperHumanId(superhuman1.getSuperHumanId());
        superHumanOrganization.setOrganizationId(organizations.getOrganizationId());
        superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization);

        SuperHumanOrganization superHumanOrganization2 = new SuperHumanOrganization();
        superHumanOrganization2.setSuperHumanId(superhuman2.getSuperHumanId());
        superHumanOrganization2.setOrganizationId(organizations2.getOrganizationId());
        superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization2);

        List<Organizations> organizationOfSuper1 = organizationsDao.getAllOrganizationsGivenSuperHuman(superhuman1.getSuperHumanId());

        List<Organizations> organizationOfSuper2 = organizationsDao.getAllOrganizationsGivenSuperHuman(superhuman2.getSuperHumanId());
        //Making sure locations has 3 locations
        assertTrue(organization.contains(organizations));
        assertTrue(organization.contains(organizations2));
        assertTrue(organization.contains(organizations3));

        //Making sure locations is size 3:
        assertEquals(3, organization.size());
        //expect 2 since there's 2 sightings associated with super human.
        //Despite 3 locations in total.
        assertEquals(1, organizationOfSuper1.size());

        assertEquals(1, organizationOfSuper2.size());
    }

    @Test
    public void addOrganization() {
        Organizations organizations = new Organizations();
        organizations.setOrganizationName("Test organization");
        organizations.setOrganizationDescription("An testing organization");
        organizations.setAddress("123 Testing");
        organizations.setContactInfo("organizations@gmail.com");
        organizationsDao.addOrganization(organizations);
        organizations = organizationsDao.addOrganization(organizations);

        Organizations fromDao = organizationsDao.getOrganizationById(organizations.getOrganizationId());

        assertEquals(organizations, fromDao);
    }

    @Test
    public void updateOrganization() {
        Organizations organizations = new Organizations();
        organizations.setOrganizationName("Test organization");
        organizations.setOrganizationDescription("An testing organization");
        organizations.setAddress("123 Testing");
        organizations.setContactInfo("organizations@gmail.com");
        organizationsDao.addOrganization(organizations);
        organizations = organizationsDao.addOrganization(organizations);

        Organizations fromDao = organizationsDao.getOrganizationById(organizations.getOrganizationId());

        assertEquals(organizations, fromDao);

        organizations.setOrganizationName("Test organization Update");
        organizations.setOrganizationDescription("An testing organization Update");
        organizations.setAddress("123 Testing Update");
        organizations.setContactInfo("updateorganizations@gmail.com");

        organizationsDao.updateOrganization(organizations);

        assertNotEquals(organizations, fromDao);

        fromDao = organizationsDao.getOrganizationById(organizations.getOrganizationId());

        assertEquals(organizations, fromDao);
    }

    @Test
    public void deleteOrganizationById() {
        Organizations organizations = new Organizations();
        organizations.setOrganizationName("Test organization");
        organizations.setOrganizationDescription("An testing organization");
        organizations.setAddress("123 Testing");
        organizations.setContactInfo("organizations@gmail.com");
        organizationsDao.addOrganization(organizations);

        Organizations organizations2 = new Organizations();
        organizations2.setOrganizationName("Test organization");
        organizations2.setOrganizationDescription("An testing organization");
        organizations2.setAddress("123 Testing");
        organizations2.setContactInfo("organizations@gmail.com");
        organizationsDao.addOrganization(organizations2);

        List<Organizations> organization = organizationsDao.getAllOrganizations();
        assertEquals(2, organization.size());
        assertTrue(organization.contains(organizations));
        assertTrue(organization.contains(organizations2));

        organizationsDao.deleteOrganizationById(organizations.getOrganizationId()); //deleting by id

        organization = organizationsDao.getAllOrganizations(); // getting total locations

        assertEquals(1, organization.size()); // making sure there's only one left, location2
        assertFalse(organization.contains(organizations)); //making sure location1 is deleted
        assertTrue(organization.contains(organizations2));
    }
}