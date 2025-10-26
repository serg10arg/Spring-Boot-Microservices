package com.mycompany.productservice.controller;

import com.mycompany.productservice.model.Inventory;
import com.mycompany.productservice.model.Price;
import com.mycompany.productservice.model.Product;
import com.mycompany.productservice.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    List<ProductInfo> productList = new ArrayList<ProductInfo>();

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/product/details/{productid}")
    public Product getProductDetails(@PathVariable Long productid) {

        // Get Name and Desc from product-service
        ProductInfo productInfo = getProductInfo(productid);

        // Get Price from pricing-service
        Price price = restTemplate.getForObject("http://localhost:8002/price/"+ productid, Price.class);

        // Get Stock Avail from inventory-service
        Inventory inventory = restTemplate.getForObject("http://localhost:8003/inventory/"+ productid, Inventory.class);

        return new Product(productInfo.getProductID(), productInfo.getProductName(), productInfo.getProductDesc(),
                price.getDiscountedPrice(), inventory.getInStock());


    }

    private ProductInfo getProductInfo(Long productid) {
        populateProductList();

        for(ProductInfo p : productList) {
            if (productid.equals(p.getProductID())) {
                return p;
            }
        }
        return null;
    }

    private void populateProductList() {
        productList.clear();
        productList.add(new ProductInfo(101L, "iPhone", "iPhone is dam expensive!"));
        productList.add(new ProductInfo(102L, "Book", "Book is great"));
        productList.add(new ProductInfo(103L, "Washing MC", "Washing MC is good"));
    }
}
