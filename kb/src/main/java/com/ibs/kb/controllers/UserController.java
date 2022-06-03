package com.ibs.kb.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ibs.kb.form.UserForm;
import com.ibs.kb.form.UserFormValidator;
import com.ibs.kb.models.Item;
import com.ibs.kb.models.User;
import com.ibs.kb.spring.mail.MailSenderService;
import com.ibs.kb.spring.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ibs.kb.Const.*;

@Controller
public class UserController {

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
    @GetMapping("/admin/users")
    public String adminUsers(Model model, HttpServletRequest request) {
        UserForm form = new UserForm();
        User user = userService.findById(userService.getUserFromPrincipal(request.getUserPrincipal()).getId());
        if (user.getRoles().equals("USER")){
            return "redirect:/";
        }
        List<User> users = userService.getList();
        model.addAttribute("users", users);
        model.addAttribute("fullName", userService.getUserFromPrincipal(request.getUserPrincipal()).getFullName());
        return "admin-users";
    }
    @GetMapping("/return-pw")
    public String userReturnPassword(Model model) {
        return "return-pw";
    }
    @PostMapping("/return-pw")
    public String userReturnPasswordSubmit(@RequestParam String email) {
        String pw = userService.findByEmail(email).get().getPassword();
        mailSender.sendEmail(email, "PasswordReturn", "Yours password : " + pw);
        return "/";
    }
}

