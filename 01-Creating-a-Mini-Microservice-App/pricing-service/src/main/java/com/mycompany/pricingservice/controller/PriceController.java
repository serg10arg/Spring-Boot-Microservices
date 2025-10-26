package com.mycompany.pricingservice.controller;

import com.mycompany.pricingservice.model.ExgVal;
import com.mycompany.pricingservice.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PriceController {

    @Autowired
    private RestTemplate restTemplate;

    List<Price> priceList = new ArrayList<Price>();

    @GetMapping("/price/{productid}")
    public Price getPriceDetails(@PathVariable Long productid) {
        Price price = getPriceInfo(productid);

        // Get Exchange Value
        Integer exgVal = restTemplate.getForObject("http://localhost:8004/currexg/from/USD/to/YEN", ExgVal.class)
                .getExgVal();

        return new Price(price.getPriceID(), price.getProductID(), price.getOriginalPrice(),
                Math.multiplyExact(exgVal, price.getDiscountedPrice()));
    }

    private Price getPriceInfo(Long productid) {
        populatepriceList();

        for(Price p : priceList) {
            if(productid.equals(p.getProductID())) {
                return p;
            }
        }
        return null;
    }

    private void populatepriceList() {
        priceList.clear();
        priceList.add(new Price(201L, 101l, 1000, 900));
        priceList.add(new Price(202L, 102l, 200, 180));
        priceList.add(new Price(203L, 103l, 500, 450));
    }
}
