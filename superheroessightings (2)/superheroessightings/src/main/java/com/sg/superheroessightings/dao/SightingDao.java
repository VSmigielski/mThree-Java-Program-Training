package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Sighting;

import java.util.List;

public interface SightingDao {
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    List<Sighting> getAllSightingsGivenDate(String sightingDate); // 3
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);
    List<Sighting> getMostRecentSightings();
}
