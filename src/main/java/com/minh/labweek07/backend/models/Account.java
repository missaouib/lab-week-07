package com.minh.labweek07.backend.models;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account" )
public class Account implements Serializable {
    @Id
    @Column(name = "account_id",columnDefinition = "varchar(50)")
    private String accountID;
    @Column(name = "full_name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    private boolean status;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Grant.class ,orphanRemoval = true,mappedBy = "account")
    private List<Grant> grants=new ArrayList<>();
    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    private Customer customer;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
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




    public Account(String accountID, String name, String password, String email, String phone, boolean status) {
        this.accountID = accountID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public Account() {
    }

//    public List<Grant> getGrants() {
//        return grants;
//    }

//    public Account(String accountID, String name, String password, String email, String phone, boolean status, List<Grant> grants) {
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

    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
