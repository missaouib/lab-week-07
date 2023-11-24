package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     List<Employee> findEmployeesByEmailIsContainingIgnoreCase(String query);


    public
    List<Employee> findEmployeesByFullNameContainingIgnoreCase(String query);
    List<Employee> findEmployeesByPhoneIsContainingIgnoreCase(String query);


}