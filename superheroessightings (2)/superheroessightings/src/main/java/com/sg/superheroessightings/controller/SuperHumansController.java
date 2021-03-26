package com.sg.superheroessightings.controller;

import com.sg.superheroessightings.dao.*;
import com.sg.superheroessightings.entities.Location;
import com.sg.superheroessightings.entities.Organizations;
import com.sg.superheroessightings.entities.Powers;
import com.sg.superheroessightings.entities.SuperHumans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
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
    OrganizationsDao organizationsDao;

    @Autowired
    PowersDao powersDao;

    @Autowired
    SuperHumansDao superhumansDao;

    Set<ConstraintViolation<SuperHumans>> violations = new HashSet<>();

    @GetMapping("superHumans")
    public String displaySuperHumans(Model model) {
        List<SuperHumans> supers = superhumansDao.getAllSuperHumans();
        List<Powers> powers = powersDao.getAllPowers();
        List<Organizations> organizations = organizationsDao.getAllOrganizations();
        model.addAttribute("supers", supers);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", organizations);
        model.addAttribute("errors", violations);
        return "superHumans";
    }

    @PostMapping("addSuperHumans")
    public String addSuperHumans(@Valid SuperHumans supers, Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String isEvil = request.getParameter("isEvil");
        String powerId = request.getParameter("powerId");
        String[] organizationIds = request.getParameterValues("organizationId");

        supers.setName(name);
        supers.setDescription(description);
        supers.setEvil(Boolean.parseBoolean(isEvil));
        supers.setPowers(powersDao.getPowerById(Integer.parseInt(powerId)));

        List<Organizations> organizations = new ArrayList<>();
        if (organizationIds != null) {
            for (String organizationId : organizationIds) {
                organizations.add(organizationsDao.getOrganizationById(Integer.parseInt(organizationId)));
            }
        }

        supers.setOrganizations(organizations);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(supers);

        if(violations.isEmpty() && !organizations.isEmpty()) {
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
        List<Powers> powers = powersDao.getAllPowers();
        List<Organizations> organizations = organizationsDao.getAllOrganizations();
        model.addAttribute("supers", supers);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", organizations);
        model.addAttribute("errors", violations);
        return "editSuperHuman";
    }

    // Use a post mapping to update the database with new values, check for errors, otherwise update and redirect to previous page
    @PostMapping("editSuperHuman")
    public String performEditSuperHuman(@Valid SuperHumans supers, BindingResult result, HttpServletRequest request, Model model) {
        String superHumanId = request.getParameter("superHumanId");
        String superDescription = request.getParameter("description");
        String isEvil = request.getParameter("isEvil");
        String superName = request.getParameter("name");
        String powerId = request.getParameter("powerId");
        String[] organizationIds = request.getParameterValues("organizationId");

        FieldError error;

        if(powerId.equals("")){
            error = new FieldError("superhuman", "power", "Must contain 1 power");
            result.addError(error);
        }

        if(organizationIds.length == 0){
            error = new FieldError("superhuman", "organizations", "Must contain at least 1 organization");
            result.addError(error);
        }

        supers.setSuperHumanId(Integer.parseInt(superHumanId));
        supers.setName(superName);
        supers.setDescription(superDescription);
        supers.setPowers(powersDao.getPowerById(Integer.parseInt(powerId)));
        supers.setEvil(Boolean.parseBoolean(isEvil));

        List<Organizations> organizations = new ArrayList<>();

        for(String organizationId : organizationIds){
            organizations.add(organizationsDao.getOrganizationById(Integer.parseInt(organizationId)));
        }

        supers.setOrganizations(organizations);

        if (result.hasErrors()) {
            model.addAttribute("powers", powersDao.getAllPowers());
            model.addAttribute("organizations", organizationsDao.getAllOrganizations());
            model.addAttribute("supers", supers);
            return "editSuperhuman";
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
