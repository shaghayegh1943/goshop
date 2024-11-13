
package com.goshop.demo.repository;

import com.goshop.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryName(String category);

    List<Product> findByBrand(String brandName);

    List<Product> findAllByCategoryNameAndBrand(String category, String brandName);

    List<Product> findAllByName(String name);

    List<Product> findAllByBrandAndName(String brand, String productName);

    Long countByBrandAndName(String brand, String productName);
}

