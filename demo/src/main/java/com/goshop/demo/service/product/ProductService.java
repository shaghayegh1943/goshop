package com.goshop.demo.service.product;

import com.goshop.demo.exception.ProductNotFoundException;
import com.goshop.demo.model.Category;
import com.goshop.demo.model.Product;
import com.goshop.demo.repository.CategoryRepository;
import com.goshop.demo.repository.ProductRepository;
import com.goshop.demo.request.AddProductRequest;
import com.goshop.demo.request.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor ab Spring 4.3 now im using 3.3.5
public class ProductService implements IProductService {

    //private final ProductRepository productRepository; in zusammenhang mit @RequiredArgsConstructor
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));
    }

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.
                ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() ->
                        categoryRepository.save(new Category(request.getCategory().getName()))
                );
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getQuantity(),
                request.getDescription(),
                category
        );

    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingproduct->updateExistingProduct(existingproduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("product not found to update"));
    }
private  Product updateExistingProduct(Product existingProduct, UpdateProductRequest upRequest){
        existingProduct.setName(upRequest.getName());
        existingProduct.setBrand(upRequest.getBrand());
        existingProduct.setPrice(upRequest.getPrice());
        existingProduct.setQuantity(upRequest.getQuantity());
        existingProduct.setDescription(upRequest.getDescription());
    Category category = categoryRepository.findByName(upRequest.getCategory().getName());
    existingProduct.setCategory(category);        existingProduct.setCategory(upRequest.getCategory());
        return existingProduct;
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
        return productRepository.findAllByCategoryNameAndBrand(category, brandName);
    }

    @Override
    public List<Product> getAllproductsByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> getAllproductsByBrandAndName(String brandName, String name) {
        return productRepository.findAllByBrandAndName(brandName, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String productName) {
        return productRepository.countByBrandAndName(brand, productName);
    }
}

