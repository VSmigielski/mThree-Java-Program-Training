package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Powers;
import com.sg.superheroessightings.entities.SuperHumans;
import java.util.List;

public interface SuperHumansDao {
        SuperHumans getSuperHumanById(int id);
        List<SuperHumans> getAllSuperHumans();
        List<SuperHumans> getAllSuperHumansGivenLocation(int locationId); // 1
        List<SuperHumans> getAllSuperHumansGivenOrganization(int organizationId); // 4
        SuperHumans addSuperHuman(SuperHumans superHuman);
        List<SuperHumans> getSuperHumansForPower(Powers powers);
        void updateSuperHuman(SuperHumans superHuman);
        void deleteSuperHumanById(int id);
}
