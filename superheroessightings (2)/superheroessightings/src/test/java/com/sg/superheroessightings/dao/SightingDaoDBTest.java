package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.TestApplicationConfiguration;
import com.sg.superheroessightings.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SightingDaoDBTest {

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

    @Before
    public void setUp() throws Exception {
        List<Sighting> sighting = sightingDao.getAllSightings();
        for(Sighting sightings : sighting) {
            sightingDao.deleteSightingById(sightings.getSightingId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocationById(location.getLocationId());
        }

        List<Organizations> organization = organizationsDao.getAllOrganizations();
        for(Organizations organizations : organization) {
            organizationsDao.deleteOrganizationById(organizations.getOrganizationId());
        }

        List<SuperHumans> superhumans = superhumansDao.getAllSuperHumans();
        for(SuperHumans superhuman : superhumans) {
            superhumansDao.deleteSuperHumanById(superhuman.getSuperHumanId());
        }

        List<Powers> powers = powersDao.getAllPowers();
        for(Powers power : powers) {
            powersDao.deletePowerById(power.getPowerId());
        }

    }

    @Test
    public void getAllSightings() {
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Test Description");
        location.setAddress("123 Testing Lane");
        location.setLatitude(2.33);
        location.setLongitude(4.55);
        locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("Test Location");
        location2.setLocationDescription("Test Description");
        location2.setAddress("123 Testing Lane");
        location2.setLatitude(2.33);
        location2.setLongitude(4.55);
        locationDao.addLocation(location2);

        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        // superHumans.setPowerId(powers.getPowerId());
        superHumans = superhumansDao.addSuperHuman(superHumans);

        Sighting sighting = new Sighting();
        sighting.setSightingDate("2019-02-11");
        sighting.setSuperHumanId(superHumans.getSuperHumanId());
        sighting.setLocationId(location.getLocationId());
        sightingDao.addSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate("2020-02-17");
        sighting2.setSuperHumanId(superHumans.getSuperHumanId());
        sighting2.setLocationId(location2.getLocationId());
        sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    public void getAllSightingsGivenDate() {
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Test Description");
        location.setAddress("123 Testing Lane");
        location.setLatitude(2.33);
        location.setLongitude(4.55);
        locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("Test Location 2");
        location2.setLocationDescription("Test Description 2");
        location2.setAddress("321 Testing Lane");
        location2.setLatitude(18.55);
        location2.setLongitude(123.3);
        locationDao.addLocation(location2);

        List<Location> locations = locationDao.getAllLocations();

        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        Powers powers2 = new Powers();
        powers2.setSuperpower("Speed");
        powers2 = powersDao.addPower(powers2);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        // superHumans.setPowerId(powers.getPowerId());
        superHumans = superhumansDao.addSuperHuman(superHumans);

        SuperHumans superHumans2 = new SuperHumans();
        superHumans2.setName("TestName2");
        superHumans2.setDescription("Test Super Speed");
        // superHumans2.setPowerId(powers2.getPowerId());
        superHumans2 = superhumansDao.addSuperHuman(superHumans2);

        Sighting sighting1 = new Sighting();
        sighting1.setSightingDate("2019-02-11");
        sighting1.setSuperHumanId(superHumans.getSuperHumanId());
        sighting1.setLocationId(location.getLocationId());
        sightingDao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate("2020-02-17");
        sighting2.setSuperHumanId(superHumans.getSuperHumanId());
        sighting2.setLocationId(location2.getLocationId());
        sightingDao.addSighting(sighting2);

        Sighting sighting3 = new Sighting();
        sighting3.setSightingDate("2020-02-17");
        sighting3.setSuperHumanId(superHumans.getSuperHumanId());
        sighting3.setLocationId(location2.getLocationId());
        sightingDao.addSighting(sighting3);

        Sighting sighting4 = new Sighting();
        sighting4.setSightingDate("2020-02-17");
        sighting4.setSuperHumanId(superHumans.getSuperHumanId());
        sighting4.setLocationId(location.getLocationId());
        sightingDao.addSighting(sighting4);

        List<Sighting> sightings = sightingDao.getAllSightings();

        // Make sure there are 4 sightings & size = 4
        assertEquals(4, sightings.size());
        assertTrue(sightings.contains(sighting1));
        assertTrue(sightings.contains(sighting2));
        assertTrue(sightings.contains(sighting3));
        assertTrue(sightings.contains(sighting4));

        List<Sighting> sightingsByDate = sightingDao.getAllSightingsGivenDate("2020-02-17");

        // expect 3 since the date fits
        // Despite 4 sightings
        assertEquals(3, sightingsByDate.size());

    }

    @Test
    public void addAndGetSighting() {
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Test Description");
        location.setAddress("123 Testing Lane");
        location.setLatitude(2.33);
        location.setLongitude(4.55);
        locationDao.addLocation(location);

        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        // superHumans.setPowerId(powers.getPowerId());
        superHumans = superhumansDao.addSuperHuman(superHumans);

        Sighting sighting = new Sighting();
        sighting.setSightingDate("2019-02-11");
        sighting.setSuperHumanId(superHumans.getSuperHumanId());
        sighting.setLocationId(location.getLocationId());
        sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals(sighting, fromDao);
    }

    @Test
    public void updateSighting() {
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Test Description");
        location.setAddress("123 Testing Lane");
        location.setLatitude(2.33);
        location.setLongitude(4.55);
        locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("Test Location 2");
        location2.setLocationDescription("Test Description 2");
        location2.setAddress("123 Testing Lane 2");
        location2.setLatitude(122.22);
        location2.setLongitude(12.99);
        locationDao.addLocation(location2);

        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        // superHumans.setPowerId(powers.getPowerId());
        superHumans = superhumansDao.addSuperHuman(superHumans);

        SuperHumans superHumans2 = new SuperHumans();
        superHumans2.setName("TestName");
        superHumans2.setDescription("Test Super Strong");
        // superHumans2.setPowerId(powers.getPowerId());
        superHumans2 = superhumansDao.addSuperHuman(superHumans2);

        Sighting sighting = new Sighting();
        sighting.setSightingDate("2019-11-27");
        sighting.setSuperHumanId(superHumans.getSuperHumanId());
        sighting.setLocationId(location.getLocationId());
        sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals(sighting, fromDao);

        sighting.setSightingDate("2019-12-23");
        sighting.setSuperHumanId(superHumans2.getSuperHumanId());
        sighting.setLocationId(location2.getLocationId());

        sightingDao.updateSighting(sighting);

        assertNotEquals(sighting, fromDao);

        fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals(sighting, fromDao);
    }

    @Test
    public void deleteSightingById() {
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Test Description");
        location.setAddress("123 Testing Lane");
        location.setLatitude(2.33);
        location.setLongitude(4.55);
        locationDao.addLocation(location);

        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        SuperHumans superHumans = new SuperHumans();
        superHumans.setName("TestName");
        superHumans.setDescription("Test Super Strong");
        // superHumans.setPowerId(powers.getPowerId());
        superHumans = superhumansDao.addSuperHuman(superHumans);

        Sighting sighting = new Sighting();
        sighting.setSuperHumanId(superHumans.getSuperHumanId());
        sighting.setLocationId(location.getLocationId());
        sighting.setSightingDate("2021-01-04");
        sightingDao.addSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSuperHumanId(superHumans.getSuperHumanId());
        sighting2.setLocationId(location.getLocationId());
        sighting2.setSightingDate("2021-01-10");
        sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));

        sightingDao.deleteSightingById(sighting.getSightingId());

        sightings = sightingDao.getAllSightings();

        assertEquals(1, sightings.size());
        assertFalse(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }
}