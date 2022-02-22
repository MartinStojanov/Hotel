package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepo productRepo;

    public ProductServiceImp(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> listAll() {
        return this.productRepo.findAll();
    }

    @Override
    public Product create(String productName, int stockLimit, int stock, String supplierName, String supplierNumber) {
        Product product = new Product( productName,  stockLimit,  stock,  supplierName,  supplierNumber);
        return this.productRepo.save(product);
    }

    @Override
    public Product delete(Long id) {
        Product product = this.productRepo.findById(id).orElseThrow(RuntimeException::new);
        return product;
    }

    @Override
    public Product findById(Long id) {
        Product product = this.productRepo.findById(id).orElseThrow(RuntimeException::new);
        return product;
    }

    @Override
    public Product update(Long id, String productName, int stockLimit, int stock, String supplierName, String supplierNumber) {
       Product product = findById(id);
       product.setProductName(productName);
       product.setStockLimit(stockLimit);
       product.setStock(stock);
       product.setSupplierName(supplierName);
       product.setSupplierNumber(supplierNumber);
        return this.productRepo.save(product);
    }
}
