package com.spring.springbootwithjpa.controllers;

import com.spring.springbootwithjpa.models.entity.Category;
import com.spring.springbootwithjpa.models.entity.Supplier;
import com.spring.springbootwithjpa.models.request.CategoryRequest;
import com.spring.springbootwithjpa.models.request.OtherKeyRequest;
import com.spring.springbootwithjpa.models.request.SearchKeyRequest;
import com.spring.springbootwithjpa.models.request.SupplierRequest;
import com.spring.springbootwithjpa.models.response.SuccessResponse;
import com.spring.springbootwithjpa.services.SupplierService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAll() {
        List<Supplier> suppliers = supplierService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all product!", suppliers));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody SupplierRequest supplierRequest) {
        Supplier newSupplier = modelMapper.map(supplierRequest, Supplier.class);
        Supplier result = supplierService.create(newSupplier);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Created successfully!", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        Supplier supplier = supplierService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get by id success!", supplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        supplierService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Deleted successfully!", "-1"));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody SupplierRequest supplierRequest, @PathVariable long id) {
        Supplier existingSupplier = modelMapper.map(supplierRequest, Supplier.class);
        supplierService.update(existingSupplier, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Updated successfully!", supplierRequest));
    }

    @PostMapping("/search/supplieremail")
    public ResponseEntity findByEmail(@RequestBody SearchKeyRequest searchKeyRequest) {
        Supplier supplier = supplierService.findBySupplierEmail(searchKeyRequest.getSearchKey());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get by supplier email successfully!", supplier));
    }

    @PostMapping("/search/suppliername")
    public ResponseEntity findByNameContains(@RequestBody SearchKeyRequest searchKeyRequest) {
        List<Supplier> suppliers = supplierService.findBySupplierNameContains(searchKeyRequest.getSearchKey());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get by supplier name successfully!", suppliers));
    }
    @PostMapping("/search/suppliernameoremail")
    public ResponseEntity findByNameContainsOrEmailContains(@RequestBody OtherKeyRequest otherKeyRequest) {
        List<Supplier> suppliers = supplierService.findBySupplierNameContainsOrSupplierEmailContains(otherKeyRequest.getSearchKeyName(), otherKeyRequest.getGetSearchKeyEmail());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Get by supplier name or email successfully!", suppliers));
    }
}
