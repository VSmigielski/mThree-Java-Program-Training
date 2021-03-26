package com.sg.superheroessightings.controller;

import com.sg.superheroessightings.dao.*;
import com.sg.superheroessightings.entities.Location;
import com.sg.superheroessightings.entities.Organizations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class LocationController {

    @Autowired
    LocationDao locationDao;

    Set<ConstraintViolation<Location>> violations = new HashSet<>();


    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(String locationName, String locationDescription, String locationAddress, double latitude, double longitude) {

        Location locations = new Location();
        locations.setLocationName(locationName);
        locations.setLocationDescription(locationDescription);
        locations.setAddress(locationAddress);
        locations.setLatitude(latitude);
        locations.setLongitude(longitude);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(locations);

        if(violations.isEmpty()) {
            locationDao.addLocation(locations);
        }

        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id) {
        locationDao.deleteLocationById(id);
        return "redirect:/locations";
    }


    @GetMapping("editLocation")
    public String editLocation(Integer id, Model model) {
        Location locations = locationDao.getLocationById(id);
        model.addAttribute("locations", locations);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(@Valid Location locations, BindingResult result) {
        if(result.hasErrors()) {
            return "editLocation";
        }
        locationDao.updateLocation(locations);

        return "redirect:/locations";
    }

    @GetMapping("confirmDeleteLocation")
    public String confirmDeleteLocation(Integer id, Model model) {
        Location locations = locationDao.getLocationById(id);
        model.addAttribute("locations", locations);
        return "confirmDeleteLocation";
    }

    @GetMapping("locationDetail")
    public String locationDetail(Integer id, Model model) {
        Location locations = locationDao.getLocationById(id);
        model.addAttribute("locations", locations);
        return "locationDetail";
    }

    @GetMapping("findLocation")
    public String findLocation(Integer id, Model model) {
        List<Location> locations = locationDao.getAllLocationsGivenSuperHumans(id);
        model.addAttribute("locations", locations);
        return "findLocation";
    }

}
