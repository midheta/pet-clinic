package com.petclinicdemo.controller;

import com.petclinicdemo.model.Owner;
import com.petclinicdemo.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    private VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping("/vets")
    public String getVets(Model model) {
        model.addAttribute("owner", new Owner());
        model.addAttribute("classActiveHome", "inactive");
        model.addAttribute("classActiveOwner", "inactive");
        model.addAttribute("classActiveVets", "active");
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
