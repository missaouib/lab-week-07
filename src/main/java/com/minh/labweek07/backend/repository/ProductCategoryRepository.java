package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}