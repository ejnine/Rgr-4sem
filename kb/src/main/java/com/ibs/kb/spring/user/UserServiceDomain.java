package com.ibs.kb.spring.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import com.ibs.kb.repo.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibs.kb.form.UserForm;
import com.ibs.kb.models.User;
import com.ibs.kb.models.Role;

@Service
public class UserServiceDomain implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getList() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public boolean isUserWithEmailExist(String email) {
        return userRepository.countByEmail(email) != 0;
    }


    @Override
    public Optional<User> findByEmailAndEnabledTrue(String email) {
        return userRepository.findByEmailAndEnabledTrue(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void update(@Valid UserForm userForm) {
        User u = new User();
        BeanUtils.copyProperties(userForm, u, "password");
        u.setPassword(userForm.getPassword());//passEncoder.encode(userForm.getPassword())
        u.setRoles(Role.USER.toString());
        u.setEnabled(true);
        userRepository.save(u);
    }

    @Override
    public User findById(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        } else {
            return null;
        }
    }

    @Override
    public User getUserFromPrincipal(Principal principal) {
        Optional<User> optionalUser = this.findByEmail(principal.getName());
        User user = optionalUser.orElse(null);
        return user;
    }
}