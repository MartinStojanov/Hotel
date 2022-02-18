package com.example.demo.service;

import com.example.demo.model.AccommodationType;
import com.example.demo.model.Guests;
import com.example.demo.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    List<Product> listAll();

    Product create(String productName, int stockLimit, int stock, String supplierName,
                   String supplierNumber);

    Product delete(Long id);

    Product findById(Long id);

    Product update(Long id,String productName, int stockLimit,
                   int stock, String supplierName, String supplierNumber);
}
