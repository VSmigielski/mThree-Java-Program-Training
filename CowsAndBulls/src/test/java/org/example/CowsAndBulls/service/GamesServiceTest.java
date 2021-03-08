package org.example.CowsAndBulls.service;

import org.example.CowsAndBulls.TestApplicationConfiguration;
import org.example.CowsAndBulls.config.GamesController;
import org.example.CowsAndBulls.dao.GamesDao;
import org.example.CowsAndBulls.dao.RoundsDao;
import org.example.CowsAndBulls.model.Games;
import org.example.CowsAndBulls.model.Rounds;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = org.example.CowsAndBulls.TestApplicationConfiguration.class)
    public class GamesServiceTest {

        @Autowired
        private GamesDao gameDao;

        @Autowired
        private RoundsDao roundDao;

        @Autowired
        private GamesService service;

        @Autowired
        private GamesController gamesController;

        public GamesServiceTest() {
        }

        // Is null not expected e:0:p:2
        @Test
        void testDetermineResult1() {
            String guess = "1234";
            String answer = "2159";
            String result = service.determineResult(guess, answer);

            assertEquals("e:0:p:2", result);
        }

        // Is null not expected e:4:p:0
        @Test
        void testDetermineResult2() {
            String guess = "1234";
            String answer = "1234";
            String result = service.determineResult(guess, answer);

            assertEquals("e:4:p:0", result);
        }

        // Is null not expected e:0:p:4
        @Test
        void testDetermineResult3() {
            String guess = "1234";
            String answer = "4321";
            String result = service.determineResult(guess, answer);

            assertEquals("e:0:p:4", result);
        }

        // Is null not expected e:2:p:2
        @Test
        void testDetermineResult4() {
            String guess = "1234";
            String answer = "1324";
            String result = service.determineResult(guess, answer);

            assertEquals("e:2:p:2", result);
        }

        // Is null not expected e:0:p:0
        @Test
        void testDetermineResult5() {
            String guess = "1234";
            String answer = "5678";
            String result = service.determineResult(guess, answer);

            assertEquals("e:0:p:0", result);
        }

    }
