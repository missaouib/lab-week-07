package com.minh.labweek07.backend.models;

import com.minh.labweek07.backend.pk.GrantPK;
import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "grant_access")
@IdClass(GrantPK.class)
public class Grant {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id",columnDefinition = "varchar(50)")
    private Role role;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id",columnDefinition = "varchar(50)")
    private Account account;
    @Column(name = "is_grant")
    private boolean isGrant;
    private String note;


    public Grant(Role role, Account account, boolean isGrant, String note) {
        this.role = role;
        this.account = account;
        this.isGrant = isGrant;
        this.note = note;
    }

    public Grant() {

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isGrant() {
        return isGrant;
    }

    public void setGrant(boolean grant) {
        isGrant = grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
