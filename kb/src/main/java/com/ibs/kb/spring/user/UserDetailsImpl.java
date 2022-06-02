package com.ibs.kb.spring.user;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ibs.kb.models.Role;
import com.ibs.kb.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1878727735866989847L;
    private final User user;
    private final List<GrantedAuthority> roles = new ArrayList<>();

    public UserDetailsImpl(User user) {
        this.user = user;
        for(Role role: user.getUserRoles()) {
            GrantedAuthority auth = new SimpleGrantedAuthority(role.toString());
            roles.add(auth);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public Long getId() {
    	return user.getId();
    }
    
}
