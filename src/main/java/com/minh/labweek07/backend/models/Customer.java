package com.minh.labweek07.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long custId;
    @Column
    private String custName;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String address;
    @OneToOne
    @JoinColumn(name = "accountID")
    private Account account;
    public Customer(long custId, String custName, String email, String phone, String address) {
        this.custId = custId;
        this.custName = custName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Customer() {
    this.custId=0L;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "custId=" + custId +
                ", custName='" + custName + '\''
                ;
    }
}
