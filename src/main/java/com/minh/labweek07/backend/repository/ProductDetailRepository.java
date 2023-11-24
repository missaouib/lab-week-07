package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.enums.Color;
import com.minh.labweek07.backend.enums.Size;
import com.minh.labweek07.backend.models.Product;
import com.minh.labweek07.backend.models.ProductDetail;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM Product_Detail p WHERE p.product_id = ? AND p.color = ?;")
    public List<ProductDetail> findDistinctByProductAndColor(long id , int color);

   @Query(nativeQuery = true,value = "SELECT Color FROM product_detail WHERE product_id=?1 GROUP BY color;")
    public List<Color> findDistinctByProduct(long productID);

    public ProductDetail findProductDetailsByColorAndProductAndSize(Color color, Product product, Size size);
}