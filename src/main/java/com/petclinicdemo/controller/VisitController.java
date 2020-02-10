package com.petclinicdemo.controller;

import com.petclinicdemo.model.Pet;
import com.petclinicdemo.model.Visit;
import com.petclinicdemo.service.OwnerService;
import com.petclinicdemo.service.PetService;
import com.petclinicdemo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private VisitService visitService;
    private OwnerService ownerService;
    private PetService petService;

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model) {

        Pet pet = petService.findOneById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @Autowired
    public VisitController(VisitService visitService, OwnerService ownerService, PetService petService) {
        this.visitService = visitService;
        this.ownerService = ownerService;
        this.petService = petService;
    }


    @GetMapping("/add")
    public String initCreateVisitForm(@PathVariable Long ownerId, @PathVariable Long petId,
                                      Model model) {
        ownerService.activateNavbar(model);
        return "/pets/createVisitForm";
    }
    @PostMapping("/add")
    public String processCreateVisitForm(Visit visit, BindingResult bindingResult, Model model) {
        ownerService.activateNavbar(model);
        if(bindingResult.hasErrors()) {
            return "/pets/createVisitForm";
        } else {
            visit = visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }

    }
}
