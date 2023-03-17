package com.spring.springbootwithjpa.repository;

import com.spring.springbootwithjpa.models.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Page<Category> findByCategoryNameContains(String categoryName, Pageable pageable);
}
