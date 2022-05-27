package com.ibs.kb.controllers;

import com.ibs.kb.models.Item;
import com.ibs.kb.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/lk")
    public String lkMain(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "lk";
    }
    @GetMapping("/classic")
    public String classic(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "classic";
    }
    @GetMapping("/detective")
    public String detective(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "detective";
    }
    @GetMapping("/fan")
    public String fan(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "fan";
    }
    @GetMapping("/romance")
    public String romance(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "romance";
    }
    @GetMapping("/poesy")
    public String poesy(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "poesy";
    }
    @GetMapping("/adventures")
    public String adventures(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "adventures";
    }
}
