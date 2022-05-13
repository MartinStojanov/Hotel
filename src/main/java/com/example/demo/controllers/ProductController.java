package com.example.demo.controllers;

import com.example.demo.model.*;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ProductRepo productRepo;

    public ProductController(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
    }

    @GetMapping("/products")
    public String listProducts(Model model){

        List<Product> productList = this.productService.listAll();
        model.addAttribute("productList",productList);
        return "listProducts";
    }


    @GetMapping("/product/add")
    public String showAdd() {
        return "editProduct";
    }

    @PostMapping("/product")
    public String addProduct(@RequestParam String productName,
                             @RequestParam int stockLimit,
                             @RequestParam int stock,
                             @RequestParam String supplierName,
                             @RequestParam String supplierNumber){
        this.productService.create(productName,stockLimit,stock,supplierName,supplierNumber);
        return "redirect:/products";
    }
    @GetMapping("/product/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Product pro = this.productService.findById(id);
        model.addAttribute("pro",pro);
        return "editProduct";
    }
    @PostMapping("/product/{id}")
    public String editProduct(@PathVariable Long id,
                              @RequestParam String productName,
                              @RequestParam int stockLimit,
                              @RequestParam int stock,
                              @RequestParam String supplierName,
                              @RequestParam String supplierNumber){
        this.productService.update(id,productName,stockLimit,stock,supplierName,supplierNumber);
        return "redirect:/products";//da se smeni Strana so updated rabotnik

    }
    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.productService.delete(id);
        return "redirect:/products"; // da se smeni strana so izbrisan rabotnik
    }

    @GetMapping("/uploadPageProduct")
    public String getUploadProduct(){
        return "uploadCSVProduct";
    }

    @PostMapping(value = "/uploadProduct", consumes = "text/csv")
    public String uploadSimpleProduct(@RequestBody InputStream body) throws IOException {
        productRepo.saveAll(CsvUtils.read(Product.class, body));

        return "redirect:/products";
    }

    @PostMapping(value = "/uploadProduct", consumes = "multipart/form-data")
    public String uploadMultipartProduct(@RequestParam("file") MultipartFile file) throws IOException {
        productRepo.saveAll(CsvUtils.read(Product.class, file.getInputStream()));
        return "redirect:/products";
    }
}
