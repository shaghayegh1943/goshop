package com.goshop.demo.service.category;

import com.goshop.demo.model.Category;

import java.util.List;

public interface Icategory {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllcategories();
    Category addCategory(Category newCategory);
    Category updateCategory(Category existingCategoryToUpdate, Long id);
    void deleteCategoryById(Long id);
}
