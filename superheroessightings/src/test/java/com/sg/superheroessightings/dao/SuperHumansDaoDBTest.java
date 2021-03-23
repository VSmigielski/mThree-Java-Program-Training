package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.TestApplicationConfiguration;
import com.sg.superheroessightings.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SuperHumansDaoDBTest {

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
    public void getSuperHumanById() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        superHumans.setPowerId(powers.getPowerId());

        superHumans = superhumansDao.addSuperHuman(superHumans);

        SuperHumans fromDao = superhumansDao.getSuperHumanById(superHumans.getSuperHumanId());

        assertEquals(superHumans, fromDao);
    }

    @Test
    public void getAllSuperHumans() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powersDao.addPower(powers);

        Powers powers2 = new Powers();
        powers2.setSuperpower("Speed");
        powersDao.addPower(powers2);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        superHumans.setPowerId(powers.getPowerId());
        superhumansDao.addSuperHuman(superHumans);

        SuperHumans superHumans2 = new SuperHumans();
        superHumans2.setName("TestName2");
        superHumans2.setDescription("Test Super Speed");
        superHumans2.setPowerId(powers2.getPowerId());
        superhumansDao.addSuperHuman(superHumans2);


        List<SuperHumans> superHuman = superhumansDao.getAllSuperHumans();

        assertEquals(2, superHuman.size());
        assertTrue(superHuman.contains(superHumans));
        assertTrue(superHuman.contains(superHumans2));
    }

    @Test
    public void getAllSuperHumansGivenLocation() {
        Location location1 = new Location();
        location1.setLocationName("Test Location");
        location1.setLocationDescription("Test Description");
        location1.setAddress("123 Testing Lane");
        location1.setLatitude(2.33);
        location1.setLongitude(4.55);
        locationDao.addLocation(location1);

        Location location2 = new Location();
        location2.setLocationName("Test Location 2");
        location2.setLocationDescription("Test Description 2");
        location2.setAddress("123 Testing Lane 2");
        location2.setLatitude(5.66);
        location2.setLongitude(18.56);
        locationDao.addLocation(location2);

        List<Location> locations = locationDao.getAllLocations();

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

        Sighting sighting1 = new Sighting();
        sighting1.setSightingDate("2019-02-11");
        sighting1.setSuperHumanId(superhuman1.getSuperHumanId());
        sighting1.setLocationId(location1.getLocationId());
        sightingDao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate("2020-02-17");
        sighting2.setSuperHumanId(superhuman2.getSuperHumanId());
        sighting2.setLocationId(location1.getLocationId());
        sightingDao.addSighting(sighting2);

        List<SuperHumans> locationsOfSupers = superhumansDao.getAllSuperHumansGivenLocation(location1.getLocationId());

        //Making sure locations has 3 locations
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location2));

        //Making sure locations is size 3:
        assertEquals(2, locations.size());
        //expect 2 since there's 2 sightings associated with super human.
        //Despite 3 locations in total.
        assertEquals(2, locationsOfSupers.size());
    }

    @Test
    public void getAllSuperHumansGivenOrganization() {
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
        superHumanOrganization2.setOrganizationId(organizations.getOrganizationId());
        superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization2);

        List<SuperHumans> organizationOfSupers = superhumansDao.getAllSuperHumansGivenOrganization(organizations.getOrganizationId());

        //Making sure locations has 2 organizations
        assertTrue(organization.contains(organizations));
        assertTrue(organization.contains(organizations2));

        //Making sure organizations size is 2
        assertEquals(2, organization.size());
        //expect 2 since there's 2 sightings associated with super human.
        //Despite 3 locations in total.
        assertEquals(2, organizationOfSupers.size());
    }

    @Test
    public void addSuperHuman() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powersDao.addPower(powers);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        superHumans.setPowerId(powers.getPowerId());
        superHumans = superhumansDao.addSuperHuman(superHumans);

        SuperHumans fromDao = superhumansDao.getSuperHumanById(superHumans.getSuperHumanId());

        assertEquals(superHumans, fromDao);
    }

    @Test
    public void updateSuperHuman() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powersDao.addPower(powers);

        Powers powers2 = new Powers();
        powers2.setSuperpower("Strength");
        powersDao.addPower(powers2);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        superHumans.setPowerId(powers.getPowerId());
        superHumans = superhumansDao.addSuperHuman(superHumans);

        SuperHumans fromDao = superhumansDao.getSuperHumanById(superHumans.getSuperHumanId());

        assertEquals(superHumans, fromDao);

        superHumans.setName("Another Test Name");
        superHumans.setDescription("Another Test Description");
        superHumans.setPowerId(powers2.getPowerId());

        superhumansDao.updateSuperHuman(superHumans);

        assertNotEquals(superHumans, fromDao);

        fromDao = superhumansDao.getSuperHumanById(superHumans.getSuperHumanId());

        assertEquals(superHumans, fromDao);
    }

    @Test
    public void deleteSuperHumanById() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powersDao.addPower(powers);

        Powers powers2 = new Powers();
        powers2.setSuperpower("Strength");
        powersDao.addPower(powers2);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        superHumans.setPowerId(powers.getPowerId());
        superHumans = superhumansDao.addSuperHuman(superHumans);

        SuperHumans superHumans2 = new SuperHumans();
        superHumans2.setName("TestName");
        superHumans2.setDescription("Test Speed");
        superHumans2.setPowerId(powers2.getPowerId());
        superHumans2 = superhumansDao.addSuperHuman(superHumans2);

        List<SuperHumans> superHuman = superhumansDao.getAllSuperHumans();

        assertEquals(2, superHuman.size());
        assertTrue(superHuman.contains(superHumans));
        assertTrue(superHuman.contains(superHumans2));

        superhumansDao.deleteSuperHumanById(superHumans.getSuperHumanId());

        superHuman = superhumansDao.getAllSuperHumans();

        assertEquals(1, superHuman.size());
        assertFalse(superHuman.contains(superHumans));
        assertTrue(superHuman.contains(superHumans2));
    }
}