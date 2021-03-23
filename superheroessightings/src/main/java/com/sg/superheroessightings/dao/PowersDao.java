package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Powers;
import java.util.List;

public interface PowersDao {
        Powers getPowerById(int id);
        List<Powers> getAllPowers();
        Powers addPower(Powers superHuman);
        void updatePower(Powers superHuman);
        void deletePowerById(int id);
}
