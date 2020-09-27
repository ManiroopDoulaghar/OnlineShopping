package com.onlineshopping.onlineshopping.service;



import com.onlineshopping.onlineshopping.model.Category;
import com.onlineshopping.onlineshopping.model.Product;
import com.onlineshopping.onlineshopping.repository.CategoryRepository;
import com.onlineshopping.onlineshopping.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    /** Create a new role  */
    @Transactional
    public ResponseEntity<Object> addCategory(Category category)  {

        Category newCategory = new Category();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setCategoryDescription(category.getCategoryDescription());
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(newCategory);
        for(int i=0; i< category.getProducts().size(); i++){
            if(!productRepository.findByproductBarcode(category.getProducts().get(i).getProductBarcode()).isPresent()) {
                Product newProduct = category.getProducts().get(i);
                newProduct.setCategories(categoryList);
                Product savedProduct = productRepository.save(newProduct);
                if(! productRepository.findById(savedProduct.getId()).isPresent())
                    return ResponseEntity.unprocessableEntity().body("Category Creation Failed");
            }
            else  return   ResponseEntity.unprocessableEntity().body("Product with barcode is already Present");
        }
        return ResponseEntity.ok("Successfully created Category");
    }


    /** Delete a specified role given the id */
    public ResponseEntity<Object> deleteCategory(Long id) {
        if(categoryRepository.findById(id).isPresent()){
            categoryRepository.deleteById(id);
            if(categoryRepository.findById(id).isPresent()){
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }


    /** Update a Role */
    public ResponseEntity<Object> updateCategory(Long id, Category category) {
        if(categoryRepository.findById(id).isPresent()){
            Category newCategory = categoryRepository.findById(id).get();
            newCategory.setCategoryName(category.getCategoryName());
            newCategory.setCategoryDescription(category.getCategoryDescription());
            Category savedCategory = categoryRepository.save(newCategory);
            if(categoryRepository.findById(savedCategory.getId()).isPresent())
                return ResponseEntity.accepted().body("Category saved successfully");
            else return ResponseEntity.badRequest().body("Failed to update Category");
        } else return ResponseEntity.unprocessableEntity().body("Specified Category not found");
    }
}
