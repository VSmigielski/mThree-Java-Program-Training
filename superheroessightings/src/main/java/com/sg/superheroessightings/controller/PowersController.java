package com.sg.superheroessightings.controller;

import com.sg.superheroessightings.dao.*;
import com.sg.superheroessightings.entities.Organizations;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PowersController {
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

    Set<ConstraintViolation<Powers>> violations = new HashSet<>();

    @GetMapping("powers")
    public String displaySuperHumans(Model model) {
        List<Powers> power = powersDao.getAllPowers();
        model.addAttribute("power", power);
        model.addAttribute("errors", violations);
        return "powers";
    }

    @PostMapping("addPowers")
    public String addPowers(String superpower) {
        Powers power = new Powers();
        power.setSuperpower(superpower);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(power);

        if(violations.isEmpty()) {
           powersDao.addPower(power);
        }

        return "redirect:/powers";
    }

    @GetMapping("deletePowers")
    public String deletePowers(Integer id) {
        powersDao.deletePowerById(id);
        return "redirect:/powers";
    }


    @GetMapping("editPowers")
    public String editPowers(Integer id, Model model) {
        Powers power = powersDao.getPowerById(id);
        model.addAttribute("power", power);
        return "editPowers";
    }

    @PostMapping("editPowers")
    public String performEditPowers(@Valid Powers power, BindingResult result) {
        if(result.hasErrors()) {
            return "editPowers";
        }
        powersDao.updatePower(power);

        return "redirect:/powers";
    }

    @GetMapping("confirmDeletePowers")
    public String confirmDeletePowers(Integer id, Model model) {
        Powers power = powersDao.getPowerById(id);
        model.addAttribute("power", power);
        return "confirmDeletePowers";
    }

}
