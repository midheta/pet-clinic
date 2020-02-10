package com.petclinicdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String getHome(Model model) {
        model.addAttribute("classActiveHome", "active");
        model.addAttribute("classActiveOwner", "inactive");
        model.addAttribute("classActiveVets", "inactive");
        return "index";
    }
}
