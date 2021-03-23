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
public class LocationsDaoDBTest {

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
    public void getLocationById() {
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Test Description");
        location.setAddress("123 Testing Lane");
        location.setLatitude(2.33);
        location.setLongitude(4.55);
        location.setLocationId(1);
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocationId());

        assertEquals(location, fromDao);
    }

    @Test
    public void getAllLocations() {
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
        location2.setLatitude(5.66);
        location2.setLongitude(18.56);
        locationDao.addLocation(location2);

        List<Location> locations = locationDao.getAllLocations();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }

    @Test
    public void getAllLocationsGivenSuperHumans() {
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

        Location location3 = new Location();
        location3.setLocationName("Test Location 3");
        location3.setLocationDescription("Test Description 3");
        location3.setAddress("123 Testing Lane 3");
        location3.setLatitude(5.66);
        location3.setLongitude(18.56);
        locationDao.addLocation(location3);

        List<Location> locations = locationDao.getAllLocations();

        Powers power1 = new Powers();
        power1.setSuperpower("Mind-control");
        powersDao.addPower(power1);

        SuperHumans superhuman1 = new SuperHumans();
        superhuman1.setName("Test SuperHuman");
        superhuman1.setDescription("Test SuperHuman Description");
        superhuman1.setPowerId(power1.getPowerId());
        superhumansDao.addSuperHuman(superhuman1);

        String date1 = "2020-01-01";

        Sighting sighting1 = new Sighting();
        sighting1.setLocationId(location1.getLocationId());
        sighting1.setSuperHumanId(superhuman1.getSuperHumanId());
        sighting1.setSightingDate(date1);
        sightingDao.addSighting(sighting1);

        String date2 = "2020-01-08";

        Sighting sighting2 = new Sighting();
        sighting2.setLocationId(location2.getLocationId());
        sighting2.setSuperHumanId(superhuman1.getSuperHumanId());
        sighting2.setSightingDate(date2);
        sightingDao.addSighting(sighting2);

        List<Location> locationOfSuper = locationDao.getAllLocationsGivenSuperHumans(superhuman1.getSuperHumanId());
        //Making sure locations has 3 locations
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location2));
        assertTrue(locations.contains(location3));
        //Making sure locations is size 3:
        assertEquals(3, locations.size());
        //expect 2 since there's 2 sightings associated with super human.
        //Despite 3 locations in total.
        assertEquals(2, locationOfSuper.size());
    }

    @Test
    public void addLocation() {
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Test Description");
        location.setAddress("123 Testing Lane");
        location.setLatitude(2.33);
        location.setLongitude(4.55);
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocationId());

        assertEquals(location, fromDao);
    }

    @Test
    public void updateLocation() {
        Location location = new Location();
        location.setLocationName("Test Location");
        location.setLocationDescription("Test Description");
        location.setAddress("123 Testing Lane");
        location.setLatitude(2.33);
        location.setLongitude(4.55);
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocationId());

        assertEquals(location, fromDao);

        location.setLocationName("Another Test Location");
        location.setLocationDescription("Another Test Description");
        location.setAddress("Another 123 Testing Lane");
        location.setLatitude(2.55);
        location.setLongitude(4.89);

        locationDao.updateLocation(location);

        assertNotEquals(location, fromDao);

        fromDao = locationDao.getLocationById(location.getLocationId());

        assertEquals(location, fromDao);
    }

    @Test
    public void deleteLocationById() {
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

        List<Location> locations = locationDao.getAllLocations();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));

        locationDao.deleteLocationById(location.getLocationId());

        locations = locationDao.getAllLocations();

        assertEquals(1, locations.size());
        assertFalse(locations.contains(location));
        assertTrue(locations.contains(location2));

    }
}