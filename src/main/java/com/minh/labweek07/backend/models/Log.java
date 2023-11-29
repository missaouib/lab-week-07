package com.minh.labweek07.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "log")
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private User accountID;
    @Column(name = "login_time")

    private String loginTime;
    @Column(name = "notes")
    private String notes;

    public Log(long id, User accountID, String loginTime, String notes) {
        this.id = id;
        this.accountID = accountID;
        this.loginTime = loginTime;
        this.notes = notes;
    }

    public Log() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAccountID() {
        return accountID;
    }

    public void setAccountID(User accountID) {
        this.accountID = accountID;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
