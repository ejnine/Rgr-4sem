package com.ibs.kb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
    @GetMapping("/lk")
    public String lkMain(Model model) {
        return "lk";
    }
    @GetMapping("/classic")
    public String classic(Model model) {
        model.addAttribute("title", "Главная страница");
        return "classic";
    }
    @GetMapping("/detective")
    public String detective(Model model) {
        model.addAttribute("title", "Главная страница");
        return "detective";
    }
    @GetMapping("/fan")
    public String fan(Model model) {
        model.addAttribute("title", "Главная страница");
        return "fan";
    }
    @GetMapping("/romance")
    public String romance(Model model) {
        model.addAttribute("title", "Главная страница");
        return "romance";
    }
    @GetMapping("/poesy")
    public String poesy(Model model) {
        model.addAttribute("title", "Главная страница");
        return "poesy";
    }
    @GetMapping("/adventures")
    public String adventures(Model model) {
        model.addAttribute("title", "Главная страница");
        return "adventures";
    }
}
