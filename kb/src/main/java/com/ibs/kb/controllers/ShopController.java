package com.ibs.kb.controllers;

import com.ibs.kb.models.Item;
import com.ibs.kb.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ShopController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/lk")
    public String lkMain(Model model) {
        return "lk";
    }
    @GetMapping("/classic")
    public String classic(Model model) {
        Iterable<Item> items = itemRepository.findByStyle("classic");
        model.addAttribute("items", items);
        return "classic";
    }
    @GetMapping("/detective")
    public String detective(Model model) {
        Iterable<Item> items = itemRepository.findByStyle("detective");
        model.addAttribute("items", items);
        return "detective";
    }
    @GetMapping("/fan")
    public String fan(Model model) {
        Iterable<Item> items = itemRepository.findByStyle("fan");
        model.addAttribute("items", items);
        return "fan";
    }
    @GetMapping("/romance")
    public String romance(Model model) {
        Iterable<Item> items = itemRepository.findByStyle("romance");
        model.addAttribute("items", items);
        return "romance";
    }
    @GetMapping("/poesy")
    public String poesy(Model model) {
        Iterable<Item> items = itemRepository.findByStyle("poesy");
        model.addAttribute("items", items);
        return "poesy";
    }
    @GetMapping("/adventures")
    public String adventures(Model model) {
        Iterable<Item> items = itemRepository.findByStyle("adventures");
        model.addAttribute("items", items);
        return "adventures";
    }

    @GetMapping("/testadd")
    public String add_item(Model model) {
        return "add-item";
    }

    @PostMapping("/testadd")
    public String add_item_post(@RequestParam String name, @RequestParam String description, @RequestParam String style, @RequestParam int price, Model model) {
        Item item = new Item(name, description, style, price);
        itemRepository.save(item);
        return "redirect:/";
    }

    @GetMapping("/classic/{id}")
    public String classicOpen(@PathVariable(value = "id") long id, Model model) {
        Optional<Item> item = itemRepository.findById(id);
        ArrayList<Item> res = new ArrayList<>();
        item.ifPresent(res::add);
        model.addAttribute("item", res);
        return "show-item";
    }
}
