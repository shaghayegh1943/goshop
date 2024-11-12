package com.goshop.demo.service.product;

import com.goshop.demo.exception.ProductNotFoundException;
import com.goshop.demo.model.Product;
import com.goshop.demo.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {

    private ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                            throw new ProductNotFoundException("product not found");
                        });
    }

    @Override
    public List<Product> getAllproducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findAllByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrandName(String brandName) {
        return productRepository.findByBrand(brandName);
    }

    @Override
    public List<Product> getAllProductsByBrandAndCategory(String category, String brandName) {
        return productRepository.findAllByCategoryNameAndBrand(category,brandName);
    }

    @Override
    public List<Product> getAllproductsByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> getAllproductsByBrandAndName(String brandName, String name) {
        return productRepository.findAllByBrandAndName(brandName,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String productName) {
        return productRepository.countByBrandAndName(brand,productName);
    }
}
