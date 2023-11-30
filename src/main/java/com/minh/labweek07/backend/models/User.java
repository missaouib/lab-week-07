package com.minh.labweek07.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user" )
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userID;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    private boolean status;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Customer customer;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }
    //getAuthorities() method is used to get the list of authorities granted to the user.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
       for(Role role :this.roles){
           SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.getRoleName());
           authorities.add(authority);
           System.out.println("Granted Authority: " + role.getRoleName());
       }
       System.out.println("Authorities: " + authorities);
       return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }




    public User(Integer userId, String name, String password, String email, String phone, boolean status) {
        this.userID = userId;
        this.username = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public User() {
    }

//    public List<Grant> getGrants() {
//        return grants;
//    }

//    public User(String accountID, String name, String password, String email, String phone, boolean status, List<Grant> grants) {
//        this.accountID = accountID;
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.phone = phone;
//        this.status = status;
//        this.grants = grants;
//    }

//    public void setGrants(List<Grant> grants) {
//        this.grants = grants;
//    }



}
