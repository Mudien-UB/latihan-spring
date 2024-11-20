package com.example.POSales.service;

import java.util.List;
import java.util.UUID;

import com.example.POSales.dto.ProductRequest;
import com.example.POSales.dto.ProductResponse;
import com.example.POSales.entity.Product;

public interface ProductService{
    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse getProductById(UUID id);
    Product getOne(UUID id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(UUID id, ProductRequest productRequest);
    void deleteProduct(UUID id);
}