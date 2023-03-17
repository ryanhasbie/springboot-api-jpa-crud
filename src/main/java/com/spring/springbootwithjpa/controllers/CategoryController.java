package com.spring.springbootwithjpa.controllers;

import com.spring.springbootwithjpa.models.entity.Category;
import com.spring.springbootwithjpa.models.entity.Product;
import com.spring.springbootwithjpa.models.request.CategoryRequest;
import com.spring.springbootwithjpa.models.request.SearchKeyRequest;
import com.spring.springbootwithjpa.models.response.SuccessResponse;
import com.spring.springbootwithjpa.services.CategoryService;
import com.spring.springbootwithjpa.services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAll() {
        List<Category> categories = categoryService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all product!", categories));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category newCategory = modelMapper.map(categoryRequest, Category.class);
        Category result = categoryService.create(newCategory);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Created successfully!", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get by id success!", category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully!", "-1"));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable long id) {
        Category existingCategory = modelMapper.map(categoryRequest, Category.class);
        categoryService.update(existingCategory, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated successfully!", categoryRequest));
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Category> findByCategoryNameContains(@RequestBody SearchKeyRequest searchKeyRequest, @PathVariable int size, @PathVariable int page, @PathVariable String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return categoryService.findByCategoryNameContains(searchKeyRequest.getSearchKey(), pageable);
    }
}
