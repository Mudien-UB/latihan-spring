package com.example.POSales.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.POSales.dto.ProductRequest;
import com.example.POSales.dto.ProductResponse;
import com.example.POSales.entity.Product;
import com.example.POSales.repository.ProductRepository;
import com.example.POSales.service.ProductService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product newProduct = Product.builder().name(productRequest.getName())
                .description(productRequest.getDescription()).price(productRequest.getPrice())
                .quantity(productRequest.getQuantity()).build();
        productRepository.saveAndFlush(newProduct);
        return toProductResponse(newProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = getOne(id);
        productRepository.delete(product);
        
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productResponses = new ArrayList<>();
        productRepository.findAll().forEach(products -> productResponses.add(toProductResponse(products)));
        return productResponses;
    }

    @Override
    public Product getOne(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " not found");
        }
        return product.get();
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        Product product = getOne(id);
        return toProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(UUID id, ProductRequest productRequest) {
        Product currentProduct = getOne(id);
        currentProduct.setName(productRequest.getName());
        currentProduct.setDescription(productRequest.getDescription());
        currentProduct.setPrice(productRequest.getPrice());
        currentProduct.setQuantity(productRequest.getQuantity());
        productRepository.save(currentProduct);
        return toProductResponse(currentProduct);
    }
    
    private ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }
    
}
