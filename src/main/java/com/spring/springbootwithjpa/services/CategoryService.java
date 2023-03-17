package com.spring.springbootwithjpa.services;

import com.spring.springbootwithjpa.exception.NotFoundException;
import com.spring.springbootwithjpa.models.entity.Category;
import com.spring.springbootwithjpa.models.entity.Product;
import com.spring.springbootwithjpa.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> list() {
        List<Category> categoryList = categoryRepo.findAll();
        if (categoryList.isEmpty()) {
            throw new NotFoundException();
        }
        return categoryList;
    }

    public Category create(Category category) {
        return categoryRepo.save(category);
    }

    public Category getById(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isEmpty()) {
            throw new NotFoundException();
        }
        return category.get();
    }

    public void delete(long id) {
        try {
            Category existingCategory = getById(id);
            categoryRepo.delete(existingCategory);
        } catch (Exception e) {
            throw new NotFoundException("Id not found!");
        }
    }

    public void update(Category category, Long id) {
        try {
            Category existingCategory = getById(id);
            category.setId(existingCategory.getId());
            categoryRepo.save(category);
        } catch (Exception e) {
            throw new NotFoundException("Id not found!");
        }
    }

    public Iterable<Category> findByCategoryNameContains(String categoryName, Pageable pageable) {
        return categoryRepo.findByCategoryNameContains(categoryName, pageable);
    }
}
