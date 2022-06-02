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

    private static final int MAX_ITEM_ON_PAGE = 100;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/style/{stl}/{page}")
    public String style_show(@PathVariable(value = "page") int page, @PathVariable(value = "stl") String stl, Model model) {
        if (page < 0) {
            page = 0;
        }
        List<Item> items = itemRepository.findByStyle(stl);
        ArrayList<Item> page_items = new ArrayList<>();
        for (int i = page*MAX_ITEM_ON_PAGE; i < items.size() && i < page*MAX_ITEM_ON_PAGE + MAX_ITEM_ON_PAGE; i++){
            if (items.get(i) != null){
                page_items.add(items.get(i));
            }
        }
        model.addAttribute("item", page_items);
        model.addAttribute("stltitle", "По стилям");
        model.addAttribute("stl", stl);
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
    @GetMapping("/style/open/{id}")
    public String itemOpen(@PathVariable(value = "id") long id, Model model) {
        Optional<Item> item = itemRepository.findById(id);
        ArrayList<Item> res = new ArrayList<>();
        item.ifPresent(res::add);
        model.addAttribute("item", res);
        return "show-item-one";
    }
}
