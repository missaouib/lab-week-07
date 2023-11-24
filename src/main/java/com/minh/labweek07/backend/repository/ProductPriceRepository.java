package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Product;
import com.minh.labweek07.backend.models.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Product> {
}