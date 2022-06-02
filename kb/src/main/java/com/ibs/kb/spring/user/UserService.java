package com.ibs.kb.spring.user;


import com.ibs.kb.form.UserForm;
import com.ibs.kb.models.User;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> getList();
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndEnabledTrue(String email);
    boolean isUserWithEmailExist(String email);
	void update(@Valid UserForm userForm);
	User findById(Long userId);
    User getUserFromPrincipal(Principal principal);
}
