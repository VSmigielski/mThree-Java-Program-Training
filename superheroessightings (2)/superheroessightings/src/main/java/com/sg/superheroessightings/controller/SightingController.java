package com.sg.superheroessightings.controller;

import com.sg.superheroessightings.dao.*;
import com.sg.superheroessightings.entities.Powers;
import com.sg.superheroessightings.entities.Sighting;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SightingController {

    @Autowired
    SightingDao sightingDao;

    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sighting = sightingDao.getAllSightings();
        model.addAttribute("sighting", sighting);
        model.addAttribute("errors", violations);
        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(int superHumanId, int locationId, String sightingDate) {

        Sighting sighting = new Sighting();
        sighting.setSuperHumanId(superHumanId);
        sighting.setLocationId(locationId);
        sighting.setSightingDate(sightingDate);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sighting);

        if(violations.isEmpty()) {
            sightingDao.addSighting(sighting);
        }

        return "redirect:/sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }


    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(@Valid Sighting sighting, BindingResult result) {
        if(result.hasErrors()) {
            return "editSighting";
        }
        sightingDao.updateSighting(sighting);

        return "redirect:/sightings";
    }


    @GetMapping("confirmDeleteSightings")
    public String confirmDeleteSightings(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "confirmDeleteSightings";
    }

    @GetMapping("/")
    public String displayLatestSightings(Model model) {
        List<Sighting> sighting = sightingDao.getMostRecentSightings();
        model.addAttribute("sighting", sighting);
        return "index";
    }

    @GetMapping("sightingDetail")
    public String sightingDetail(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "sightingDetail";
    }

    @GetMapping("sightingDetails")
    public String sightingDetails(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "sightingDetails";
    }
}
