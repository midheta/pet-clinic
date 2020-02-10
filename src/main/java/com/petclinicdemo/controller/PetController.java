package com.petclinicdemo.controller;

import com.petclinicdemo.model.Owner;
import com.petclinicdemo.model.Pet;
import com.petclinicdemo.model.PetType;
import com.petclinicdemo.service.OwnerService;
import com.petclinicdemo.service.PetService;
import com.petclinicdemo.service.PetTypeService;
import com.petclinicdemo.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

    private PetService petService;
    private VetService vetService;
    private PetTypeService petTypeService;
    private OwnerService ownerService;

    @Autowired
    public PetController(PetService petService, VetService vetService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }


    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId, Model model) {

        model.addAttribute("classActiveSettings", "active");
        return ownerService.findOneById(ownerId);
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }


    @GetMapping("/add")
    public String initCreateForm(Owner owner, Model model) {
        ownerService.activateNavbar(model);
        Pet pet = Pet.builder().build();
        pet.setOwner(owner);
        model.addAttribute("owner", owner);
        model.addAttribute("pet", pet);
        //  model.addAttribute("types", petTypeService.findAll());
        return "/pets/createOrUpdatePetForm";
    }

    @PostMapping("/add")
    public String processCreateForm(Owner owner, Pet pet, BindingResult result, Model model) {
        ownerService.activateNavbar(model);
        if (StringUtils.hasLength(pet.getName()) && owner.getPets().contains(pet)) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        pet.setOwner(owner);
        petService.save(pet);
        owner.getPets().add(pet);
        return "redirect:/owners/" + owner.getId();

    }

    @GetMapping("/{petId}/edit")
    public String initEditForm(@PathVariable Long petId, Model model) {
        ownerService.activateNavbar(model);
        Pet pet = petService.findOneById(petId);
        //model.addAttribute("owner", pet.getOwner());
        model.addAttribute("pet", pet);
        return "/pets/createOrUpdatePetForm";
    }

    @PostMapping("/{petId}/edit")
    public String processEditForm(@PathVariable Long petId, Pet pet, Owner owner, Model model) {
        ownerService.activateNavbar(model);
        pet.setId(petId);
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
