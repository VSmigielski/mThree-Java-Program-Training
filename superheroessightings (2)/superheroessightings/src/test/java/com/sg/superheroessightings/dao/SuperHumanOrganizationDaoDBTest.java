//package com.sg.superheroessightings.dao;
//
//import com.sg.superheroessightings.TestApplicationConfiguration;
//import com.sg.superheroessightings.entities.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
//public class SuperHumanOrganizationDaoDBTest {
//
//    @Autowired
//    LocationDao locationDao;
//
//    @Autowired
//    OrganizationsDao organizationsDao;
//
//    @Autowired
//    PowersDao powersDao;
//
//    @Autowired
//    SightingDao sightingDao;
//
//    @Autowired
//    SuperHumansDao superhumansDao;
//
//    @Autowired
//    SuperHumanOrganizationDao superHumanOrganizationDao;
//
//    @Before
//    public void setUp() throws Exception {
//        List<Location> locations = locationDao.getAllLocations();
//        for(Location location : locations) {
//            locationDao.deleteLocationById(location.getLocationId());
//        }
//
//        List<Organizations> organization = organizationsDao.getAllOrganizations();
//        for(Organizations organizations : organization) {
//            organizationsDao.deleteOrganizationById(organizations.getOrganizationId());
//        }
//
//        List<Powers> powers = powersDao.getAllPowers();
//        for(Powers power : powers) {
//            powersDao.deletePowerById(power.getPowerId());
//        }
//
//        List<Sighting> sighting = sightingDao.getAllSightings();
//        for(Sighting sightings : sighting) {
//            sightingDao.deleteSightingById(sightings.getSightingId());
//        }
//
//        List<SuperHumanOrganization> superHumanOrganizations = superHumanOrganizationDao.getAllSuperHumanOrganization();
//        for(SuperHumanOrganization superHumanOrganization : superHumanOrganizations) {
//            superHumanOrganizationDao.deleteSuperHumanOrganizationById(superHumanOrganization.getOrganizationId());
//        }
//
//        List<SuperHumans> superhumans = superhumansDao.getAllSuperHumans();
//        for(SuperHumans superhuman : superhumans) {
//            superhumansDao.deleteSuperHumanById(superhuman.getSuperHumanId());
//        }
//
//    }
//
//    @Test
//    public void getAllSuperHumanOrganization() {
//        Organizations organizations = new Organizations();
//        organizations.setOrganizationName("Test organization");
//        organizations.setOrganizationDescription("An testing organization");
//        organizations.setAddress("123 Testing");
//        organizations.setContactInfo("organizations@gmail.com");
//        organizationsDao.addOrganization(organizations);
//
//        Organizations organizations2 = new Organizations();
//        organizations2.setOrganizationName("#2 Test organization");
//        organizations2.setOrganizationDescription("#2 An testing organization");
//        organizations2.setAddress("321 Testing");
//        organizations2.setContactInfo("organizations2@gmail.com");
//        organizationsDao.addOrganization(organizations2);
//
//        List<Organizations> organization = organizationsDao.getAllOrganizations();
//
//
//        Powers power1 = new Powers();
//        power1.setSuperpower("Mind-control");
//        powersDao.addPower(power1);
//
//        Powers power2 = new Powers();
//        power2.setSuperpower("Strength");
//        powersDao.addPower(power2);
//
//        SuperHumans superhuman1 = new SuperHumans();
//        superhuman1.setName("Test SuperHuman");
//        superhuman1.setDescription("Test SuperHuman Description");
//        // superhuman1.setPowerId(power1.getPowerId());
//        superhumansDao.addSuperHuman(superhuman1);
//
//        SuperHumans superhuman2 = new SuperHumans();
//        superhuman2.setName("Test SuperHuman");
//        superhuman2.setDescription("Test SuperHuman Description");
//        // superhuman2.setPower(powersDao.getPowerId());
//        superhumansDao.addSuperHuman(superhuman2);
//
//        SuperHumanOrganization superHumanOrganization = new SuperHumanOrganization();
//        superHumanOrganization.setSuperHumanId(superhuman1.getSuperHumanId());
//        superHumanOrganization.setOrganizationId(organizations.getOrganizationId());
//        superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization);
//
//        SuperHumanOrganization superHumanOrganization2 = new SuperHumanOrganization();
//        superHumanOrganization2.setSuperHumanId(superhuman2.getSuperHumanId());
//        superHumanOrganization2.setOrganizationId(organizations2.getOrganizationId());
//        superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization2);
//
//        List<SuperHumanOrganization> SupersAndOrgs = superHumanOrganizationDao.getAllSuperHumanOrganization();
//
//        //Making sure organizations are added
//        assertTrue(organization.contains(organizations));
//        assertTrue(organization.contains(organizations2));
//
//        //Making sure list is expected of 2
//        assertEquals(2, SupersAndOrgs.size());
//    }
//
//    @Test
//    public void addAndGetSuperHumanOrganization() {
//        Organizations organizations = new Organizations();
//        organizations.setOrganizationName("Test organization");
//        organizations.setOrganizationDescription("An testing organization");
//        organizations.setAddress("123 Testing");
//        organizations.setContactInfo("organizations@gmail.com");
//        organizationsDao.addOrganization(organizations);
//
//        Powers power1 = new Powers();
//        power1.setSuperpower("Mind-control");
//        power1 = powersDao.addPower(power1);
//
//        SuperHumans superhuman1 = new SuperHumans();
//        superhuman1.setName("Test SuperHuman");
//        superhuman1.setDescription("Test SuperHuman Description");
//        // superhuman1.setPowerId(power1.getPowerId());
//        superhuman1 = superhumansDao.addSuperHuman(superhuman1);
//
//        SuperHumanOrganization superHumanOrganization = new SuperHumanOrganization();
//        superHumanOrganization.setShoId(1);
//        superHumanOrganization.setOrganizationId(organizations.getOrganizationId());
//        superHumanOrganization.setSuperHumanId(superhuman1.getSuperHumanId());
//        superHumanOrganization = superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization);
//
//        SuperHumanOrganization fromDao = superHumanOrganizationDao.getSuperHumanOrganizationById(superHumanOrganization.getShoId());
//
//        assertEquals(superHumanOrganization, fromDao);
//    }
//
//    @Test
//    public void updateSuperHumanOrganization() {
//        Organizations organizations = new Organizations();
//        organizations.setOrganizationName("Test organization");
//        organizations.setOrganizationDescription("An testing organization");
//        organizations.setAddress("123 Testing");
//        organizations.setContactInfo("organizations@gmail.com");
//        organizationsDao.addOrganization(organizations);
//
//        Organizations organizations2 = new Organizations();
//        organizations2.setOrganizationName("#2 Test organization");
//        organizations2.setOrganizationDescription("#2 An testing organization");
//        organizations2.setAddress("321 Testing");
//        organizations2.setContactInfo("organizations2@gmail.com");
//        organizationsDao.addOrganization(organizations2);
//
//        Powers power1 = new Powers();
//        power1.setSuperpower("Mind-control");
//        powersDao.addPower(power1);
//
//        Powers power2 = new Powers();
//        power2.setSuperpower("Strength");
//        powersDao.addPower(power2);
//
//        SuperHumans superhuman1 = new SuperHumans();
//        superhuman1.setName("Test SuperHuman");
//        superhuman1.setDescription("Test SuperHuman Description");
//        // superhuman1.setPowerId(power1.getPowerId());
//        superhumansDao.addSuperHuman(superhuman1);
//
//        SuperHumans superhuman2 = new SuperHumans();
//        superhuman2.setName("Test SuperHuman");
//        superhuman2.setDescription("Test SuperHuman Description");
//        // superhuman2.setPowerId(power2.getPowerId());
//        superhumansDao.addSuperHuman(superhuman2);
//
//        SuperHumanOrganization superHumanOrganization = new SuperHumanOrganization();
//        superHumanOrganization.setSuperHumanId(superhuman1.getSuperHumanId());
//        superHumanOrganization.setOrganizationId(organizations.getOrganizationId());
//        superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization);
//
//        SuperHumanOrganization fromDao = superHumanOrganizationDao.getSuperHumanOrganizationById(superHumanOrganization.getShoId());
//
//        assertEquals(superHumanOrganization, fromDao);
//
//        superHumanOrganization.setOrganizationId(organizations2.getOrganizationId());
//        superHumanOrganization.setSuperHumanId(superhuman2.getSuperHumanId());
//
//        superHumanOrganizationDao.updateSuperHumanOrganization(superHumanOrganization);
//
//        assertNotEquals(superHumanOrganization, fromDao);
//
//        fromDao = superHumanOrganizationDao.getSuperHumanOrganizationById(superHumanOrganization.getShoId());
//
//        assertEquals(superHumanOrganization, fromDao);
//    }
//
//    @Test
//    public void deleteSuperHumanOrganizationById() {
//        Organizations organizations = new Organizations();
//        organizations.setOrganizationName("Test organization");
//        organizations.setOrganizationDescription("An testing organization");
//        organizations.setAddress("123 Testing");
//        organizations.setContactInfo("organizations@gmail.com");
//        organizationsDao.addOrganization(organizations);
//
//        Organizations organizations2 = new Organizations();
//        organizations2.setOrganizationName("#2 Test organization");
//        organizations2.setOrganizationDescription("#2 An testing organization");
//        organizations2.setAddress("321 Testing");
//        organizations2.setContactInfo("organizations2@gmail.com");
//        organizationsDao.addOrganization(organizations2);
//
//        Powers powers = new Powers();
//        powers.setSuperpower("Strength");
//        powersDao.addPower(powers);
//
//        Powers powers2 = new Powers();
//        powers2.setSuperpower("Speed");
//        powersDao.addPower(powers2);
//
//        SuperHumans superHumans = new SuperHumans();
//        superHumans.setName("TestName");
//        superHumans.setDescription("Test Super Strong");
//        // superHumans.setPowerId(powers.getPowerId());
//        superHumans = superhumansDao.addSuperHuman(superHumans);
//
//        SuperHumans superHumans2 = new SuperHumans();
//        superHumans2.setName("TestName");
//        superHumans2.setDescription("Test Speed");
//        // superHumans2.setPowerId(powers2.getPowerId());
//        superHumans2 = superhumansDao.addSuperHuman(superHumans2);
//
//        SuperHumanOrganization superHumanOrganization = new SuperHumanOrganization();
//        superHumanOrganization.setSuperHumanId(superHumans.getSuperHumanId());
//        superHumanOrganization.setOrganizationId(organizations.getOrganizationId());
//        superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization);
//
//        SuperHumanOrganization superHumanOrganization2 = new SuperHumanOrganization();
//        superHumanOrganization2.setSuperHumanId(superHumans2.getSuperHumanId());
//        superHumanOrganization2.setOrganizationId(organizations.getOrganizationId());
//        superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganization2);
//
//        List<SuperHumanOrganization> superHumanOrganizations = superHumanOrganizationDao.getAllSuperHumanOrganization();
//
//        assertEquals(2, superHumanOrganizations.size());
//        assertTrue(superHumanOrganizations.contains(superHumanOrganization));
//        assertTrue(superHumanOrganizations.contains(superHumanOrganization2));
//
//        superHumanOrganizationDao.deleteSuperHumanOrganizationById(superHumanOrganization.getShoId());
//
//        superHumanOrganizations = superHumanOrganizationDao.getAllSuperHumanOrganization();
//
//        assertEquals(1, superHumanOrganizations.size());
//        assertFalse(superHumanOrganizations.contains(superHumanOrganization));
//        assertTrue(superHumanOrganizations.contains(superHumanOrganization2));
//    }
//}