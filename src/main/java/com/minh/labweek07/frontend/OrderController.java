package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.models.Order;
import com.minh.labweek07.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/admin/orders/{id}")
    public String orderDetailPage(Model model, @PathVariable("id") long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "admin/order-detail";
        } else {
            return "error";
        }
    }

}
