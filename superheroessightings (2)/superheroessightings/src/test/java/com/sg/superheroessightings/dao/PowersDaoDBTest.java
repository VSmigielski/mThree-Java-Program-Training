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
public class PowersDaoDBTest {

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

        List<SuperHumans> superhumans = superhumansDao.getAllSuperHumans();
        for(SuperHumans superhuman : superhumans) {
            superhumansDao.deleteSuperHumanById(superhuman.getSuperHumanId());
        }
    }

    @Test
    public void getPowerById() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        Powers fromDao = powersDao.getPowerById(powers.getPowerId());

        assertEquals(powers, fromDao);
    }

    @Test
    public void getAllPowers() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powersDao.addPower(powers);

        Powers powers2 = new Powers();
        powers2.setSuperpower("Speed");
        powersDao.addPower(powers2);

        List<Powers> power = powersDao.getAllPowers();

        assertEquals(2, power.size());
        assertTrue(power.contains(powers));
        assertTrue(power.contains(powers2));
    }

    @Test
    public void addPower() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        Powers fromDao = powersDao.getPowerById(powers.getPowerId());

        assertEquals(powers, fromDao);
    }

    @Test
    public void updatePower() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        Powers fromDao = powersDao.getPowerById(powers.getPowerId());

        assertEquals(powers, fromDao);

        powers.setSuperpower("Speed");

        powersDao.updatePower(powers);

        assertNotEquals(powers, fromDao);

        fromDao = powersDao.getPowerById(powers.getPowerId());

        assertEquals(powers, fromDao);
    }

    @Test
    public void deletePowerById() {
        Powers powers = new Powers();
        powers.setSuperpower("Strength");
        powers = powersDao.addPower(powers);

        Powers powers2 = new Powers();
        powers2.setSuperpower("Speed");
        powers2 = powersDao.addPower(powers2);

        List<Powers> power = powersDao.getAllPowers();

        assertEquals(2, power.size());
        assertTrue(power.contains(powers));
        assertTrue(power.contains(powers2));

        powersDao.deletePowerById(powers.getPowerId());

        power = powersDao.getAllPowers();

        assertEquals(1, power.size());
        assertFalse(power.contains(powers));
        assertTrue(power.contains(powers2));
    }
}