package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomersByCustNameContaining(String name);
    @Query("SELECT c FROM Customer c WHERE c.email LIKE %?1%")
    List<Customer> findCustomersByEmailIsContainingIgnoreCase(String email);
    @Query("SELECT c FROM Customer c WHERE c.phone LIKE %?1%")
    List<Customer> findCustomersByPhoneIsContainingIgnoreCase(String phone);
    Customer findCustomerByEmail(String email);
    @Query(nativeQuery = true, value = "SELECT * FROM customers WHERE user_id = ?1")
    List<Customer> findCustomerByAccountAccountID(Integer accountID);


}