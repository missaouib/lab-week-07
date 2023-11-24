package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.models.*;
import com.minh.labweek07.backend.repository.CustomerRepository;
import com.minh.labweek07.backend.repository.EmployeeRepository;
import com.minh.labweek07.backend.repository.OrderRepository;
import com.minh.labweek07.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller

public class AdminController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/admin")
    public String showAdminPage(){
        return "admin/admin";
    }
    @GetMapping("/admin/product")
    public String showAdminProductPage(Model model, @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(5)));
        model.addAttribute("products",productPage.getContent());
        model.addAttribute("productPage", productPage);
        model.addAttribute("status",ProductStatus.values());
        return "admin/product";
    }
    @GetMapping("/admin/customer")
    public String showAdminCustomerPage(Model model, @RequestParam("page") Optional<Integer> page,
                                        @RequestParam("size") Optional<Integer> size){
        Page<Customer> customerPage= customerRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(5)));
        model.addAttribute("customers",customerPage.getContent());
        model.addAttribute("customerPage", customerPage);
        return "admin/customer";
    }
    @GetMapping("/admin/orders")
    public String orderPage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        Page<Order> orderPage = orderRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(5)));
        model.addAttribute("orderPage", orderPage);
        return "admin/order";
    }
    @GetMapping("/admin/employee")
    public String showAdminEmployeePage(Model model){
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "admin/employee";
    }
}
