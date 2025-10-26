package com.mycompany.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private Long priceID;
    private Long productID;
    private Integer originalPrice;
    private Integer discountedPrice;
}
