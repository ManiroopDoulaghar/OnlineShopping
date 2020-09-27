package com.onlineshopping.onlineshopping.repository;

import com.onlineshopping.onlineshopping.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByCategoryName(String categoryName);

}
