package com.example.g130ministore.service.impl;

import com.example.g130ministore.entity.Product;
import com.example.g130ministore.repository.ProductRepository;
import com.example.g130ministore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public double priceOfAllProducts() {
        return productRepository.findAll().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
