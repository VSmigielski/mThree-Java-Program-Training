package com.sg.superheroessightings.controller;

import com.sg.superheroessightings.dao.*;
import com.sg.superheroessightings.entities.Sighting;
import com.sg.superheroessightings.entities.SuperHumanOrganization;
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
public class SuperHumanOrganizationController {
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

    Set<ConstraintViolation<SuperHumanOrganization>> violations = new HashSet<>();

    @GetMapping("superHumanOrganization")
    public String displaySuperHumanOrganization(Model model) {
        List<SuperHumanOrganization> superHumanOrganizations = superHumanOrganizationDao.getAllSuperHumanOrganization();
        model.addAttribute("superHumanOrganizations", superHumanOrganizations);
        model.addAttribute("errors", violations);
        return "superHumanOrganization";
    }

    @PostMapping("addSuperHumanOrganization")
    public String addSuperHumanOrganization(int superHumanId, int organizationId) {

        SuperHumanOrganization superHumanOrganizations = new SuperHumanOrganization();
        superHumanOrganizations.setSuperHumanId(superHumanId);
        superHumanOrganizations.setOrganizationId(organizationId);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superHumanOrganizations);

        if(violations.isEmpty()) {
            superHumanOrganizationDao.addSuperHumanOrganization(superHumanOrganizations);
        }

        return "redirect:/superHumanOrganization";
    }

    @GetMapping("deleteSuperHumanOrganization")
    public String deleteSuperHumanOrganization(Integer id) {
        superHumanOrganizationDao.deleteSuperHumanOrganizationById(id);
        return "redirect:/superHumanOrganization";
    }


    @GetMapping("editSuperHumanOrganization")
    public String editSuperHumanOrganization(Integer id, Model model) {
        SuperHumanOrganization superHumanOrganizations = superHumanOrganizationDao.getSuperHumanOrganizationById(id);
        model.addAttribute("superHumanOrganizations", superHumanOrganizations);
        return "editSuperHumanOrganization";
    }

    @PostMapping("editSuperHumanOrganization")
    public String performEditSuperHumanOrganization(@Valid SuperHumanOrganization superHumanOrganizations, BindingResult result) {
        if(result.hasErrors()) {
            return "editSuperHumanOrganization";
        }
        superHumanOrganizationDao.updateSuperHumanOrganization(superHumanOrganizations);

        return "redirect:/superHumanOrganization";
    }

    @GetMapping("confirmDeleteSuperHumanOrganization")
    public String confirmDeleteSuperHumanOrganizations(Integer id, Model model) {
        SuperHumanOrganization superHumanOrganizations = superHumanOrganizationDao.getSuperHumanOrganizationById(id);
        model.addAttribute("superHumanOrganizations", superHumanOrganizations);
        return "confirmDeleteSuperHumanOrganization";
    }
}
