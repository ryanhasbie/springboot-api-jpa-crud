package com.spring.springbootwithjpa.services;

import com.spring.springbootwithjpa.exception.NotFoundException;
import com.spring.springbootwithjpa.models.entity.Category;
import com.spring.springbootwithjpa.models.entity.Supplier;
import com.spring.springbootwithjpa.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> list() {
        List<Supplier> supplierList = supplierRepository.findAll();
        if (supplierList.isEmpty()) {
            throw new NotFoundException();
        }
        return supplierList;
    }

    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier getById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) {
            throw new NotFoundException();
        }
        return supplier.get();
    }

    public void delete(long id) {
        try {
            Supplier existingSupplier = getById(id);
            supplierRepository.delete(existingSupplier);
        } catch (Exception e) {
            throw new NotFoundException("Id not found!");
        }
    }

    public void update(Supplier supplier, Long id) {
        try {
            Supplier existingSupplier = getById(id);
            supplier.setId(existingSupplier.getId());
            supplierRepository.save(supplier);
        } catch (Exception e) {
            throw new NotFoundException("Id not found!");
        }
    }

    public Supplier findBySupplierEmail(String supplierEmail) {
        return supplierRepository.findBySupplierEmail(supplierEmail);
    }

    public List<Supplier> findBySupplierNameContains(String supplierName) {
        return supplierRepository.findBySupplierNameContains(supplierName);
    }

    public List<Supplier> findBySupplierNameContainsOrSupplierEmailContains(String supplierName, String supplierEmail) {
        return supplierRepository.findBySupplierNameContainsOrSupplierEmailContains(supplierName, supplierEmail);
    }
}
