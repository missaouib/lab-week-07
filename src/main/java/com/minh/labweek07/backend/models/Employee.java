package com.minh.labweek07.backend.models;


import com.minh.labweek07.backend.converter.EmployeeStatusConverter;
import com.minh.labweek07.backend.converter.ProductStatusConverter;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emp_id;

    @Column
    private String fullName;

    @Column
    private String dob;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String address;
    @Convert(converter = EmployeeStatusConverter.class)

    private EmployeeStatus employeeStatus;

    public Employee(long emp_id, String fullName, String dob, String email, String phone, String address, EmployeeStatus employeeStatus) {
        this.emp_id = emp_id;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.employeeStatus = employeeStatus;
    }

    public Employee() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public EmployeeStatus getStatus() {
        return employeeStatus;
    }

    public void setStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", employeeStatus=" + employeeStatus +
                '}';
    }
}
