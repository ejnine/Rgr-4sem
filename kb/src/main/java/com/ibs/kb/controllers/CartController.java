package com.ibs.kb.controllers;



import com.ibs.kb.form.UserForm;
import com.ibs.kb.models.Cart;
import com.ibs.kb.models.Item;
import com.ibs.kb.models.User;
import com.ibs.kb.repo.CartRepository;
import com.ibs.kb.repo.ItemRepository;
import com.ibs.kb.repo.UserRepository;
import com.ibs.kb.spring.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class CartController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/cartupdate")
    public String cartUpdate(Model model) {
        return "";
    }

    @PostMapping("/cartupdate")
    public String cartUpdatePost(@RequestParam int id_item, @RequestParam int id_buyer, @RequestParam String status, Model model, HttpServletRequest request) {
        id_buyer = userService.getUserFromPrincipal(request.getUserPrincipal()).getId().intValue();
        Cart cart = new Cart(id_buyer, id_item, status);
        cartRepository.save(cart);
        return "redirect:/";
    }

    @GetMapping("/cartNewStatus")
    public String cartNewStatus(Model model) {
        return "redirect:/lk";
    }

    @PostMapping("/cartNewStatus")
    public String cartNewStatus(@RequestParam int id_item, @RequestParam int id_buyer, @RequestParam String status,
                                @RequestParam Long id, Model model, HttpServletRequest request) {
        User user = userService.findById((long)id_buyer);
        id_buyer = userService.getUserFromPrincipal(request.getUserPrincipal()).getId().intValue();
        cartRepository.deleteById(id);
        int itemPrice = itemRepository.findById((long) id_item).get().getPrice();
        if (status.equals("WISH")){
            status = "CART";
        } else {
            if (status.equals("CART")) {
                if(user.getBalance() - itemPrice < 0){
                    status = "WISH";
                } else {
                    user.setBalance(user.getBalance() - itemPrice);
                    status = "SOLD";
                }
                userRepository.save(user);
            } else {
                if (!status.equals("SOLD")) {
                    status = "WISH";
                }
            }
        }
        Cart cart = new Cart(id_buyer, id_item, status);
        cartRepository.save(cart);
        return "redirect:/lk";
    }

    //userService.getUserFromPrincipal(request.getUserPrincipal()).getId().intValue() = id_buyer
    @GetMapping("/lk")
    public String lkMain(Model model, HttpServletRequest request) {
        Long idBuyer = userService.getUserFromPrincipal(request.getUserPrincipal()).getId();
        List<Cart> cartsAll = (List<Cart>) cartRepository.findAll();
        List<Cart> carts = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < cartsAll.size(); i++){
            if (cartsAll.get(i).getIdBuyer() == idBuyer){
                carts.add(cartsAll.get(i));
                items.add(itemRepository.findById((long) cartsAll.get(i).getIdItem()).get());
            }
        }
        model.addAttribute("items", items);
        model.addAttribute("carts", carts);
        model.addAttribute("fullName", userService.getUserFromPrincipal(request.getUserPrincipal()).getFullName());
        model.addAttribute("balance", userService.getUserFromPrincipal(request.getUserPrincipal()).getBalance());
        return "lk";
    }
}