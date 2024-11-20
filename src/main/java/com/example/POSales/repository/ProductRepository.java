package com.example.POSales.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.POSales.entity.Product;

public interface ProductRepository extends JpaRepositoryImplementation<Product, UUID> {

}