package com.onlineshopping.onlineshopping.service;


import com.onlineshopping.onlineshopping.model.Product;
import com.onlineshopping.onlineshopping.repository.CategoryRepository;
import com.onlineshopping.onlineshopping.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    /** Create a new Product */
    public ResponseEntity<Object> createProduct(Product model) {
        Product product = new Product();
        if (productRepository.findByproductBarcode(model.getProductBarcode()).isPresent()) {
            return ResponseEntity.badRequest().body("The Barcode is already Present, Failed to Create new Product");
        } else {
            product.setProductName(model.getProductName());
            product.setProductWeight(model.getProductWeight());
            product.setProductBarcode(model.getProductBarcode());
            product.setCategories(model.getCategories());

            Product savedProduct = productRepository.save(product);
            if (productRepository.findById(savedProduct.getId()).isPresent())
                return ResponseEntity.ok("Product Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating Product as Specified");
        }
    }

    /** Update an Existing Product */
    @Transactional
    public ResponseEntity<Object> updateProduct(Product product, Long id) {
        if(productRepository.findById(id).isPresent()) {
            Product newProduct = productRepository.findById(id).get();
            newProduct.setProductName(product.getProductName());
            newProduct.setProductWeight(product.getProductWeight());
            newProduct.setProductBarcode(product.getProductBarcode());
            newProduct.setCategories(product.getCategories());
            Product savedProduct = productRepository.save(newProduct);
            if(productRepository.findById(savedProduct.getId()).isPresent())
                return  ResponseEntity.accepted().body("Product updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the Product specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the Product specified");
    }
    /** Delete an User*/
    public ResponseEntity<Object> deleteProduct(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            if (productRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified Product");
            else return ResponseEntity.ok().body("Successfully deleted the specified Product");
        } else return ResponseEntity.badRequest().body("Cannot find the Product specified");
    }
}

