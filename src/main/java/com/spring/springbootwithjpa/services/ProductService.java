package com.spring.springbootwithjpa.services;

import com.spring.springbootwithjpa.exception.NotFoundException;
import com.spring.springbootwithjpa.models.entity.Category;
import com.spring.springbootwithjpa.models.entity.Product;
import com.spring.springbootwithjpa.models.entity.Supplier;
import com.spring.springbootwithjpa.repository.CategoryRepo;
import com.spring.springbootwithjpa.repository.ProductRepository;
import com.spring.springbootwithjpa.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    SupplierService supplierService;


    public List<Product> list() {
            List<Product> productList = productRepository.findAll();
            if (productList.isEmpty()) {
                throw new NotFoundException();
            }
            return productList;
    }

    public Product create(Product product, Long categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        product.setCategory(category.get());
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException();
        }
        return product.get();
    }

    public void delete(Long id) {
        try {
            Product existingProduct = getById(id);
            productRepository.delete(existingProduct);
        } catch (Exception e) {
            throw new  NotFoundException("Id Not Found!");
        }
    }

    public void update(Product product, Long id) {
        try {
            Product existingProduct = getById(id);
            product.setId(existingProduct.getId());
            productRepository.save(product);
        } catch (Exception e) {
            throw new NotFoundException("Id Not Found");
        }
    }

    public List<Product> findByProductName(String productName) {
        List<Product> products = productRepository.findByProductName(productName);
        if (products.isEmpty()) {
            throw new NotFoundException("Product name "+productName+" not found!");
        }
        return products;
    }

    public List<Product> findByCategory(Long categoryId) {
        List<Product> products= productRepository.findByCategory(categoryId);
        if (products.isEmpty()) {
            throw new NotFoundException("Category not found!");
        }
        return products;
    }

    public List<Product> findBySupplier(Long supplierId) {
        Supplier supplier = supplierService.getById(supplierId);
        if (supplier == null) {
            throw new NotFoundException("Supplier not found!");
        }
        return productRepository.findBySuppliers(supplier);
    }

}
