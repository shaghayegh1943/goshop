
package com.goshop.demo.service.product;

import com.goshop.demo.model.Product;
import com.goshop.demo.request.AddProductRequest;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    Product addProduct(AddProductRequest request);
    void updateProduct(Product product, Long productId);
    void deleteProductById(Long id);
    List<Product> getAllproducts();
    List<Product> getProductByCategory(String category);
    List<Product> getProductByBrandName(String brandName);
    List<Product> getAllProductsByBrandAndCategory(String category, String brandName);
    List<Product> getAllproductsByName(String name);
    List<Product> getAllproductsByBrandAndName(String brandName, String name);
    Long countProductsByBrandAndName(String brand, String productName);
}

