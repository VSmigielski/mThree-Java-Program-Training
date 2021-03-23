package com.sg.superheroessightings.controller;

import com.sg.superheroessightings.dao.*;
import com.sg.superheroessightings.entities.Location;
import com.sg.superheroessightings.entities.Powers;
import com.sg.superheroessightings.entities.SuperHumans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SuperHumansController {
    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    PowersDao powersDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperHumansDao superhumansDao;

    @Autowired
    SuperHumanOrganizationDao superHumanOrganizationDao;

    Set<ConstraintViolation<SuperHumans>> violations = new HashSet<>();

    @GetMapping("superHumans")
    public String displaySuperHumans(Model model) {
        List<SuperHumans> supers = superhumansDao.getAllSuperHumans();
        model.addAttribute("supers", supers);
        model.addAttribute("errors", violations);
        return "superHumans";
    }

    @PostMapping("addSuperHumans")
    public String addSuperHumans(String name, String description, String powerId) {
        SuperHumans supers = new SuperHumans();
        supers.setName(name);
        supers.setDescription(description);
        supers.setPowerId(Integer.parseInt(powerId));

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(supers);

        if(violations.isEmpty()) {
            superhumansDao.addSuperHuman(supers);
        }

        return "redirect:/superHumans";
    }

    // To delete a super human by id pulled from database/html, redirects to super humans
    @GetMapping("deleteSuperHuman")
    public String deleteSuperHuman(Integer id) {
        superhumansDao.deleteSuperHumanById(id);
        return "redirect:/superHumans";
    }

    // Use a get mapping to direct to this page, then populate the fields using the supers object
    @GetMapping("editSuperHuman")
    public String editSuperHuman(Integer id, Model model) {
        SuperHumans supers = superhumansDao.getSuperHumanById(id);
        model.addAttribute("supers", supers);
        return "editSuperHuman";
    }

    // Use a post mapping to update the database with new values, check for errors, otherwise update and redirect to previous page
    @PostMapping("editSuperHuman")
    public String performEditSuperHuman(@Valid SuperHumans supers, BindingResult result) {
        if(result.hasErrors()) {
            return "editSuperHuman";
        }
       superhumansDao.updateSuperHuman(supers);

        return "redirect:/superHumans";
    }

    // Use a get mapping to direct to this page, use the value of id to get the details of the object, use an attribute to attach the fields
    // into the html page
    @GetMapping("superHumansDetail")
    public String superHumansDetail(Integer id, Model model) {
        SuperHumans supers = superhumansDao.getSuperHumanById(id);
        model.addAttribute("supers", supers);
        return "superHumansDetail";
    }

    // Use a get mapping to direct to this page, use the id value retrieved as a hidden element in html
    // If user hits "yes", super human will be deleted using the id
    @GetMapping("confirmDeleteSuperHumans")
    public String confirmDeleteSuperHumans(Integer id, Model model) {
        SuperHumans supers = superhumansDao.getSuperHumanById(id);
        model.addAttribute("supers", supers);
        return "confirmDeleteSuperHumans";
    }

    // Use a get mapping to retrieve the locations and names of super humans
    @GetMapping("superHumanNamesLocations")
    public String superHumanNamesLocations() {
        return "superHumanNamesLocations";
    }

    // Use a post mapping to direct to this page, retrieve and use the locationId to populate the object, and redirect to reload
    @PostMapping("superHumanNamesLocations")
    public String superHumanNamesLocations(Integer locationId, Model model){
        List<SuperHumans> supers = superhumansDao.getAllSuperHumansGivenLocation(locationId);
        model.addAttribute("supers", supers);
        return "redirect:/superHumanNamesLocations";
    }
}
