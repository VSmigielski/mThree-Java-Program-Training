package com.sg.superheroessightings.dao;

import com.sg.superheroessightings.entities.Location;
import java.util.List;

public interface LocationDao {
    Location getLocationById(int id);
    List<Location> getAllLocations();
    List<Location> getAllLocationsGivenSuperHumans(int superHumanId); // 2
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationById(int id);
}