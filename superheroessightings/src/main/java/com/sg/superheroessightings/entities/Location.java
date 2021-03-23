package com.sg.superheroessightings.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


public class Location {
    private int locationId;

    @NotBlank(message = "Location's Name must not be empty.")
    @Size(max = 50, message="Location's Name must be less than 50 characters.")
    public String locationName;
    @NotBlank(message = "Location's Description must not be empty.")
    @Size(max = 50, message="Location's Description must be less than 50 characters.")
    public String locationDescription;
    @NotBlank(message = "Location's Address must not be empty.")
    @Size(max = 256, message="Location's Address must be less than 255 characters.")
    public String address;
    public double latitude;
    public double longitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId == location.locationId && Double.compare(location.latitude, latitude) == 0 && Double.compare(location.longitude, longitude) == 0 && Objects.equals(locationName, location.locationName) && Objects.equals(locationDescription, location.locationDescription) && Objects.equals(address, location.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, locationName, locationDescription, address, latitude, longitude);
    }
}
