package com.spring.springbootwithjpa.repository;

import com.spring.springbootwithjpa.models.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierEmail(String supplierEmail);
    List<Supplier> findBySupplierNameContains(String supplierName);
    List<Supplier> findBySupplierNameContainsOrSupplierEmailContains(String supplierName, String supplierEmail);
}
