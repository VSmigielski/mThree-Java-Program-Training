package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.SuperHumanOrganization;
import com.sg.superheroessightings.entities.SuperHumans;


import java.util.List;

public interface SuperHumanOrganizationDao {
    SuperHumanOrganization getSuperHumanOrganizationById(int shoId);
    List<SuperHumanOrganization> getAllSuperHumanOrganization();
    SuperHumanOrganization addSuperHumanOrganization(SuperHumanOrganization superHumanOrganization);
    void updateSuperHumanOrganization(SuperHumanOrganization superHumanOrganization);
    void deleteSuperHumanOrganizationById(int shoId);

}
