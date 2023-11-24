package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.models.ProductDetail;
import com.minh.labweek07.backend.repository.ProductDetailRepository;
import com.minh.labweek07.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DetailProduct {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
}
