package com.petclinicdemo.controller;

import com.petclinicdemo.model.Owner;
import com.petclinicdemo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private OwnerService ownerService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/index", "/index.html"})
    public String getOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        ownerService.activateNavbar(model);
        return "/owners/index";
    }

    @RequestMapping("/find")
    String findOwner(Model model) {
        model.addAttribute("owner", new Owner());
        ownerService.activateNavbar(model);
        return "/owners/findOwner";
    }

    @GetMapping
    String processFindOwnerForm(Owner owner, BindingResult result, Model model) {
        ownerService.activateNavbar(model);
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        List<Owner> results = ownerService.findAllByLastName(owner.getLastName());
        if (results.isEmpty()) {
            result.rejectValue("lastName", "not found");
            return "owners/findOwner";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("owners", results);
            return "owners/index";
        }
    }

    @GetMapping("/{ownerId}")
    String showOwner(@PathVariable("ownerId") Long ownerId, Model model) {
        ownerService.activateNavbar(model);
        Owner owner = ownerService.findOneById(ownerId);
        //  ModelAndView mav = new ModelAndView("/owners/ownerDetails");
        //   mav.addObject(owner);
        //   return mav;
        model.addAttribute("owner", owner);
        return "/owners/ownerDetails";
    }

    @RequestMapping("/add")
    String initAddOwner(Model model) {
        ownerService.activateNavbar(model);
        model.addAttribute("owner", Owner.builder().build());
        return "/owners/addOrUpdateOwner";
    }

    @PostMapping("/add")
    String processAddOwner(Owner owner, BindingResult result, Model model) {
        ownerService.activateNavbar(model);
        if (result.hasErrors()) {
            return "/owners/addOrUpdateOwner";
        }
        owner = ownerService.save(owner);
        model.addAttribute("owner", owner);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        ownerService.activateNavbar(model);
        model.addAttribute(ownerService.findOneById(ownerId));
        return "/owners/addOrUpdateOwner";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable Long ownerId, @Valid Owner owner, BindingResult result,
                                         Model model) {
        ownerService.activateNavbar(model);
        if (result.hasErrors()) {
            return "/owners/addOrUpdateOwner";
        }
        owner.setId(ownerId);
        owner = ownerService.save(owner);
        return "redirect:/owners/" + owner.getId();
    }


}
