package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Organizations;
import java.util.List;

public interface OrganizationsDao {
    Organizations getOrganizationById(int id);
    List<Organizations> getAllOrganizations();
    List<Organizations> getAllOrganizationsGivenSuperHuman(int superHumanId); //5
    Organizations addOrganization(Organizations organizations);
    void updateOrganization(Organizations organizations);
    void deleteOrganizationById(int id);
}