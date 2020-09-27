package com.onlineshopping.onlineshopping.repository;

import com.onlineshopping.onlineshopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByproductBarcode(String productBarcode);
    @Query(value = "delete  from t_products_categories where prodcuts_id= :product_id", nativeQuery = true)
    void deleteRelation(@Param("product_id") Long product_id);
}
