package com.minh.labweek07.backend.pk;

import com.minh.labweek07.backend.models.Order;
import com.minh.labweek07.backend.models.ProductDetail;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class OrderDetailPK implements Serializable {
    private Order order;
    private ProductDetail productDetail;
}
