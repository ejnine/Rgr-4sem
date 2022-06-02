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

import java.util.*;

@Controller
public class ShopController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/lk")
    public String lkMain(Model model) {
        return "lk";
    }
    @GetMapping("/classic/{page}")
    public String classic(@PathVariable(value = "page") int page, Model model) {
        List<Item> items = itemRepository.findByStyle("classic");
        ArrayList<Item> page_items = new ArrayList<>();
        for (int i = page*10; i < items.size() && i < page*10 + 10; i++){
            if (items.get(i) != null){
                page_items.add(items.get(i));
            }
        }
        model.addAttribute("item", page_items);
        model.addAttribute("stltitle", "Классика");
        model.addAttribute("stl", "classic");
        model.addAttribute("page", page);
        return "show-item";
    }

    @GetMapping("/detective/{page}")
    public String detective(@PathVariable(value = "page") int page, Model model) {
        List<Item> items = itemRepository.findByStyle("detective");
        ArrayList<Item> page_items = new ArrayList<>();
        for (int i = page*10; i < items.size() && i < page*10 + 10; i++){
            if (items.get(i) != null){
                page_items.add(items.get(i));
            }
        }
        model.addAttribute("item", page_items);
        model.addAttribute("stltitle", "Детектив");
        model.addAttribute("stl", "detective");
        model.addAttribute("page", page);
        return "show-item";
    }
    @GetMapping("/fan/{page}")
    public String fan(@PathVariable(value = "page") int page, Model model) {
        List<Item> items = itemRepository.findByStyle("fan");
        ArrayList<Item> page_items = new ArrayList<>();
        for (int i = page*10; i < items.size() && i < page*10 + 10; i++){
            if (items.get(i) != null){
                page_items.add(items.get(i));
            }
        }
        model.addAttribute("item", page_items);
        model.addAttribute("stltitle", "Фантастика");
        model.addAttribute("stl", "fan");
        model.addAttribute("page", page);
        return "show-item";
    }
    @GetMapping("/romance/{page}")
    public String romance(@PathVariable(value = "page") int page, Model model) {
        List<Item> items = itemRepository.findByStyle("romance");
        ArrayList<Item> page_items = new ArrayList<>();
        for (int i = page*10; i < items.size() && i < page*10 + 10; i++){
            if (items.get(i) != null){
                page_items.add(items.get(i));
            }
        }
        model.addAttribute("item", page_items);
        model.addAttribute("stltitle", "Романтика");
        model.addAttribute("stl", "romance");
        model.addAttribute("page", page);
        return "show-item";
    }
    @GetMapping("/poesy/{page}")
    public String poesy(@PathVariable(value = "page") int page, Model model) {
        List<Item> items = itemRepository.findByStyle("poesy");
        ArrayList<Item> page_items = new ArrayList<>();
        for (int i = page*10; i < items.size() && i < page*10 + 10; i++){
            if (items.get(i) != null){
                page_items.add(items.get(i));
            }
        }
        model.addAttribute("item", page_items);
        model.addAttribute("stltitle", "Поэзия");
        model.addAttribute("stl", "poesy");
        model.addAttribute("page", page);
        return "show-item";
    }
    @GetMapping("/adventures/{page}")
    public String adventures(@PathVariable(value = "page") int page, Model model) {
        List<Item> items = itemRepository.findByStyle("adventures");
        ArrayList<Item> page_items = new ArrayList<>();
        for (int i = page*10; i < items.size() && i < page*10 + 10; i++){
            if (items.get(i) != null){
                page_items.add(items.get(i));
            }
        }
        model.addAttribute("item", page_items);
        model.addAttribute("stltitle", "Приключения");
        model.addAttribute("stl", "adventures");
        model.addAttribute("page", page);
        return "show-item";
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
    @GetMapping("/open/{id}")
    public String itemOpen(@PathVariable(value = "id") long id, Model model) {
        Optional<Item> item = itemRepository.findById(id);
        ArrayList<Item> res = new ArrayList<>();
        item.ifPresent(res::add);
        model.addAttribute("item", res);
        return "show-item-one";
    }
}
