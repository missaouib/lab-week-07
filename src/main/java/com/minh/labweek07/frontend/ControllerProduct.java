package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerProduct {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/product")
    public String showHomeProductPage(Model model ){
        model.addAttribute("products", productRepository.findAll().stream().limit(10).toList());
        return "admin/product";
    }
}

