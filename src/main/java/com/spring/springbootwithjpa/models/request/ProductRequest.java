package com.spring.springbootwithjpa.models.request;

import com.spring.springbootwithjpa.models.entity.Category;
import com.spring.springbootwithjpa.models.entity.Supplier;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class ProductRequest {
    @NotBlank(message = "Product name is required!")
    private String productName;
    @NotBlank(message = "Product price is required!")
    private String productPrice;
    private Category category;
    private Set<Supplier> suppliers = new HashSet<>();

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", category=" + category +
                '}';
    }
}
