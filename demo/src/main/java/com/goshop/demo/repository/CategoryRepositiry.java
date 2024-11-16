package com.goshop.demo.repository;

import com.goshop.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositiry extends JpaRepository<Category,Long> {
}
