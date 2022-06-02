package com.ibs.kb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private String token;
    private String roles;

    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<Role> getUserRoles() {
        Set<Role> userRoles = new HashSet<>();

        if (roles != null && roles.length() > 0) {

            String[] rolesArr = roles.split(",");
            for (String role : rolesArr) {
                userRoles.add(Role.valueOf(role));
            }
        }
        return userRoles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", fname=" + firstName + ", lname=" + lastName + ", password=" + password + "]";
    }

//    public String getHighLevelRole() {
//
//        List<String> allRoles = new ArrayList<>();
//
//        for (Role role : this.getUserRoles()) {
//            allRoles.add(role.toString());
//        }
//        if (allRoles.contains(Role.ADMIN.toString())) {
//            return Role.ADMIN.toString();
//        } else {
//            return Role.USER.toString();
//        }
//    }

//    public List<String> getRolesList() {
//        List<String> list = new ArrayList<>();
//        this.getUserRoles().toArray();
//        for (Role role : this.getUserRoles()) {
//            list.add(role.toString());
//        }
//        return list;
//    }

//    private String convertRoleSetToString(Set<Role> roleSet) {
//        List<String> roleArr = new ArrayList<>(roleSet.size());
//        roleSet.forEach(c -> roleArr.add(c.toString()));
//        return String.join(",", roleArr);
//    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof User))
            return false;
        User user = (User)obj;
        if (user.hashCode() == this.hashCode())
            return true;
        return false;
    }

}
