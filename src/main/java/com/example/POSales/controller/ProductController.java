package com.example.POSales.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.POSales.dto.ProductRequest;
import com.example.POSales.dto.ProductResponse;
import com.example.POSales.service.ProductService;
import com.example.POSales.utility.ResponseUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse entity = productService.addProduct(productRequest);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, "Product added successfully", entity);
    }

    @GetMapping("/view")
    public ResponseEntity<?> getProductById(@RequestParam UUID id) {
        return ResponseUtil.buildResponse(HttpStatus.OK, "Product found successfully",
                productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseUtil.buildResponse(HttpStatus.OK, "Products found successfully",
                productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody ProductRequest productRequest) {
        ProductResponse entity = productService.updateProduct(id, productRequest);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Product updated successfully", entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Product deleted successfully", null);
    }

}
