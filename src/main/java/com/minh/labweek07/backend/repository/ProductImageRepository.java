package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}