package com.onlineshopping.onlineshopping.controller;


import com.onlineshopping.onlineshopping.model.Product;
import com.onlineshopping.onlineshopping.repository.ProductRepository;
import com.onlineshopping.onlineshopping.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping("/product/create")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @GetMapping("/product/details/{id}")
    public Product getProduct(@PathVariable Long id) {
        if(productRepository.findById(id).isPresent())
            return productRepository.findById(id).get();
        else return  null;
    }
    @GetMapping("/product/all")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @PutMapping("/product/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(product, id);
    }
    @DeleteMapping("product/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
