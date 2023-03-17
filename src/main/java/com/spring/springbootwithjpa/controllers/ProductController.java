package com.spring.springbootwithjpa.controllers;

import com.spring.springbootwithjpa.models.entity.Category;
import com.spring.springbootwithjpa.models.entity.Product;
import com.spring.springbootwithjpa.models.entity.Supplier;
import com.spring.springbootwithjpa.models.request.ProductRequest;
import com.spring.springbootwithjpa.models.request.SearchKeyRequest;
import com.spring.springbootwithjpa.models.response.SuccessResponse;
import com.spring.springbootwithjpa.services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;


    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAll() {
            List<Product> products = productService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all product!", products));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ProductRequest productRequest) {
        Long categoryId = productRequest.getCategory().getId();
        Product newProduct = modelMapper.map(productRequest, Product.class);
        Product result = productService.create(newProduct, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Created successfully!", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        Product product = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get by id success!", product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully!", "-1"));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody ProductRequest productRequest, @PathVariable long id) {
        Product existingProduct = modelMapper.map(productRequest, Product.class);
        productService.update(existingProduct, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated successfully!", productRequest));
    }
    @PostMapping("/search/productname")
    public ResponseEntity searchByName(@RequestBody SearchKeyRequest searchKeyRequest) {
        List<Product> products = productService.findByProductName(searchKeyRequest.getSearchKey());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Search successfully!", products));
    }

    @GetMapping("/search/category/{categoryId}")
    public ResponseEntity searchByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.findByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Search category successfully!", products));
    }

    @GetMapping("/search/supplier/{supplierId}")
    public ResponseEntity searchBySupplier(@PathVariable Long supplierId) {
        List<Product> products = productService.findBySupplier(supplierId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Search supplier successfully!", products));
    }

}
