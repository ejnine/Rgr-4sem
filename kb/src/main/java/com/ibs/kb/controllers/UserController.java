package com.ibs.kb.controllers;

import javax.validation.Valid;

import com.ibs.kb.form.UserForm;
import com.ibs.kb.form.UserFormValidator;
import com.ibs.kb.models.User;
import com.ibs.kb.spring.mail.MailSenderService;
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

    private String EMAIL_USERS_SUB = "Книжный бульвар - Регистрация";
    private String EMAIL_USERS_BODY_0 = "Здравствуйте, ";
    private String EMAIL_USERS_BODY_1 = ", спасибо, что зарегистрировались в нашем Книжном Бульваре!";

    @Autowired
    private MailSenderService mailSender;

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
            mailSender.sendEmail(userForm.getEmail(), EMAIL_USERS_SUB,
                    EMAIL_USERS_BODY_0 + userForm.getFirstName() + EMAIL_USERS_BODY_1);
            userService.update(userForm);
        }
        return "redirect:/";
    }
}

