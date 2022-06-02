package com.ibs.kb.controllers;

import javax.validation.Valid;

import com.ibs.kb.form.UserForm;
import com.ibs.kb.form.UserFormValidator;
import com.ibs.kb.models.User;
import com.ibs.kb.spring.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private final UserFormValidator validator;

    public UserController(UserFormValidator validator) {
        this.validator = validator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("/user/list")
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "/user/list";
    }


    @GetMapping(path = {"/user/registration/{userId}", "/user/registration"})
    public String userRegistration(Model model, @PathVariable(required = false) Long userId) {

        UserForm form = new UserForm();
        if (userId != null && userId > 0) {
            User user = userService.findById(userId);
            BeanUtils.copyProperties(user, form, "password", "token");
        }
        model.addAttribute("userForm", form);
        return "/user/registration";
    }


    @PostMapping("/user/registration")
    public String userRegistrationSubmit(@ModelAttribute @Valid UserForm userForm,
        BindingResult result) {

        if (result.hasErrors()) {
            return "/user/registration";
        } else {
            userService.update(userForm);
        }
        return "redirect:/";
    }
}

