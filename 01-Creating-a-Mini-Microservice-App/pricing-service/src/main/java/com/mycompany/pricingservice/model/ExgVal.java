package com.mycompany.pricingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExgVal {

    private Long id;
    private Currencies from;
    private Currencies to;
    private Integer exgVal;
}
