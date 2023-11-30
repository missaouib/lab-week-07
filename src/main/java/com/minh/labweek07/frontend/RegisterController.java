package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.models.Customer;
import com.minh.labweek07.backend.models.Role;
import com.minh.labweek07.backend.models.User;
import com.minh.labweek07.backend.repository.CustomerRepository;
import com.minh.labweek07.backend.repository.RoleRepository;
import com.minh.labweek07.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model){
        String notification="";
        model.addAttribute("notification",notification);
        User user=new User();
        model.addAttribute("user",user);
        return "register";


    }
    @PostMapping("/register")
    public String register(Model model, User user, @RequestParam("address") String address){
        String notification="";
        if(userRepository.existsByUsername(user.getUsername())){
            notification="Username already exists";
            model.addAttribute("notification",notification);
            return "register";
        }
        List<Role> roles=new ArrayList<>();
        Role role=new Role("user","user","active",user);
        roles.add(role);

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Customer customer=new Customer();
        customer.setAddress(address);
        customer.setUser(user);
        customer.setCustName(user.getUsername());
        customer.setEmail(user.getEmail());
        customer.setPhone(user.getPhone());
        customerRepository.save(customer);
        return "register";
    }
}
