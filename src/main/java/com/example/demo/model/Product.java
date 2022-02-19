package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private int stockLimit;
    private int stock;
    private String supplierName;
    private String supplierNumber;

    public Product() {
    }

    public Product(String productName, int stockLimit, int stock, String supplierName, String supplierNumber) {
        this.productName = productName;
        this.stockLimit = stockLimit;
        this.stock = stock;
        this.supplierName = supplierName;
        this.supplierNumber = supplierNumber;
    }
}
