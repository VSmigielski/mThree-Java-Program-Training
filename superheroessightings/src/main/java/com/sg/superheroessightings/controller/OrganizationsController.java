package com.sg.superheroessightings.controller;

import com.sg.superheroessightings.dao.*;
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
public class OrganizationsController {
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

    Set<ConstraintViolation<Organizations>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organizations> organization = organizationsDao.getAllOrganizations();
        model.addAttribute("organization", organization);
        model.addAttribute("errors", violations);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(String organizationName, String organizationDescription,
                                  String address, String contactInfo) {

        Organizations organization = new Organizations();
        organization.setOrganizationName(organizationName);
        organization.setOrganizationDescription(organizationDescription);
        organization.setAddress(address);
        organization.setContactInfo(contactInfo);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if(violations.isEmpty()) {
            organizationsDao.addOrganization(organization);
        }

        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model) {
        Organizations organization = organizationsDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(@Valid Organizations organization, BindingResult result) {
        if(result.hasErrors()) {
            return "editOrganization";
        }
        organizationsDao.updateOrganization(organization);

        return "redirect:/organizations";
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(Integer id, Model model) {
        Organizations organization = organizationsDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "organizationDetail";
    }

    @GetMapping("confirmDeleteOrganization")
    public String confirmDeleteOrganization(Integer id, Model model) {
        Organizations organization = organizationsDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "confirmDeleteOrganization";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {
        organizationsDao.deleteOrganizationById(id);
        return "redirect:/organizations";
    }
}
