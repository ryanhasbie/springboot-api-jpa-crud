package com.spring.springbootwithjpa.repository;

import com.spring.springbootwithjpa.models.entity.Product;
import com.spring.springbootwithjpa.models.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    List<Product> findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    List<Product> findByCategory(Long categoryId);

    @Query("SELECT p FROM Product  p WHERE ?1 MEMBER OF p.suppliers")
    List<Product> findBySuppliers(Supplier supplier);
}
