package com.onlineshopping.onlineshopping.controller;



import com.onlineshopping.onlineshopping.model.Category;
import com.onlineshopping.onlineshopping.repository.CategoryRepository;
import com.onlineshopping.onlineshopping.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CategoryController {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/category/create")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        return  categoryService.addCategory(category);
    }
    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
    @GetMapping("/category/details/{id}")
    public Category getCategory(@PathVariable Long id) {
        if(categoryRepository.findById(id).isPresent())
            return categoryRepository.findById(id).get();
        else return null;
    }
    @GetMapping("/category/all")
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }
    @PutMapping("/category/update/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }


}
