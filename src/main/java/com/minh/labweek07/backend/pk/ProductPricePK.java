package com.minh.labweek07.backend.pk;

import com.minh.labweek07.backend.enums.Size;
import com.minh.labweek07.backend.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class ProductPricePK implements Serializable {
    private Product product;
    private Date priceDate;

}
