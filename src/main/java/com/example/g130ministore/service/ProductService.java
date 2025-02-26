package com.example.g130ministore.service;

import com.example.g130ministore.entity.Product;

import java.util.List;

public interface ProductService {

    double priceOfAllProducts();

    void addProducts(List<Product> products);
}
