package com.sg.superheroessightings.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Sighting {
        public int sightingId;
    public int superHumanId;
    public int locationId;
    @NotBlank(message = "Sighting Date must not be empty.")
    @Size(max = 20, message="Sighting Date must be less than 20 characters.")
    public String sightingDate;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getSuperHumanId() {
        return superHumanId;
    }

    public void setSuperHumanId(int superHumanId) {
        this.superHumanId = superHumanId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(String sightingDate) {
        this.sightingDate = sightingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return sightingId == sighting.sightingId && superHumanId == sighting.superHumanId && locationId == sighting.locationId && Objects.equals(sightingDate, sighting.sightingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sightingId, superHumanId, locationId, sightingDate);
    }
}

