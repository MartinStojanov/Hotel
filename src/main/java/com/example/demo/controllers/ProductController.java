package com.example.demo.controllers;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/listProducts")
    public String listProducts(Model model){

        List<Product> productList = this.productService.listAll();
        model.addAttribute("productList",productList);
        return "listProducts";
    }

    //eden Get Mapping so input forma za addReservation
    //TODO:
    //@GetMapping


    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String productName,
                             @RequestParam int stockLimit,
                             @RequestParam int stock,
                             @RequestParam String supplierName,
                             @RequestParam String supplierNumber){
        this.productService.create(productName,stockLimit,stock,supplierName,supplierNumber);
        return "redirect:/listProducts";//da se smeni Strana so nov gost
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
}
