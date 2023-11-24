package com.minh.labweek07.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long order_id;
    @Column
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new LinkedHashSet<OrderDetail>();

    public Order(long order_id, Date orderDate, Employee employee, Customer customer) {
        this.order_id = order_id;
        this.orderDate = orderDate;
        this.employee = employee;
        this.customer = customer;
    }

    public Order(Customer customer, Set<OrderDetail> orderDetails) {
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.orderDate= new Date(System.currentTimeMillis());
    }

    public Order(long order_id, Date orderDate, Employee employee, Customer customer, Set<OrderDetail> orderDetails) {
        this.order_id = order_id;
        this.orderDate = orderDate;
        this.employee = employee;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public Order() {
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
