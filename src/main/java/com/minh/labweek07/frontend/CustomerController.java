package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.models.Customer;
import com.minh.labweek07.backend.repository.CustomerRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/add-customer")
    public String addCustomer() {
        return "admin/add-customer";
    }
    @PostMapping("/handle-add-customer")
    public String handleAddCustomer(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("address") String address ,@RequestParam("phone") String phone) {
        Customer customer = new Customer();
        customer.setCustName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customerRepository.save(customer);
        return "redirect:/admin/customer";
    }
    @GetMapping("/edit-customer")
    public String editCustomer(@RequestParam("id") Long id,@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("address") String address ,@RequestParam("phone") String phone) {
        Customer customer = customerRepository.findById(id).get();
        customer.setCustName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customerRepository.save(customer);
        return "admin/customer";
    }
    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
        return "redirect:/admin/customer";
    }
    @GetMapping("/update-customer")
    public String updateCustomer(@RequestParam("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id).get();
        model.addAttribute("customer", customer);

        return "admin/update-customer";
    }
    @GetMapping("/search-customer")
    public String searchCustomer(@RequestParam("query") String query, Model model) {
        List<Customer> customer;
        customer=customerRepository.findCustomersByCustNameContaining(query);
        if(customer.size()==0){
            customer=customerRepository.findCustomersByEmailIsContainingIgnoreCase(query);
            model.addAttribute("customers", customer);
        }

        if(customer.size()==0){
            customer=customerRepository.findCustomersByPhoneIsContainingIgnoreCase(query);
            model.addAttribute("customers", customer);
        }
        model.addAttribute("customers", customer);
        return "admin/customer";
    }

}
