package com.goshop.demo.service.category;

import com.goshop.demo.exception.CategoryNotFoundException;
import com.goshop.demo.model.Category;
import com.goshop.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class categoryService implements Icategory {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("there was no category with catId " + id));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllcategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category newCategory) {

        return Optional.of(newCategory)
                .filter(category -> !categoryRepository.existsByName(newCategory.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new CategoryNotFoundException("can not add a new category.it is already exists"));
    }


    @Override
    public Category updateCategory(Category existingCategoryToUpdate, Long id) {
        return Optional.ofNullable(categoryRepository.findById(id))
                .map(oldCategory -> {
                    oldCategory.get().setName(existingCategoryToUpdate.getName());
                    return categoryRepository.save(existingCategoryToUpdate);
                }).orElseThrow(() -> new CategoryNotFoundException("not found"));

    }


    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete,
                        () -> {
                            throw new CategoryNotFoundException("we could not find category with id " + id + " to delete");
                        });
    }
}
