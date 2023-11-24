package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Product;
import com.minh.labweek07.backend.models.ProductStatus;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAllByOrderByProductIdDesc();
    public List<Product> findProductsByStatus(ProductStatus status);

    public List<Product> findProductsByDescriptionContaining(String description);
    public List<Product> findProductsByNameContainingIgnoreCase(String query);
    public Page<Product> findProductsByProductCategoryId(long id, org.springframework.data.domain.Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT p.* FROM products p INNER JOIN product_detail pd ON p.product_id = pd.product_id WHERE pd.color = :id GROUP BY p.product_id")
    Page<Product> findProductsByColorId(@Param("id") int id, Pageable pageable);
}