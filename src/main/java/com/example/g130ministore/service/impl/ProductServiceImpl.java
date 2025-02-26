package com.example.g130ministore.service.impl;

import com.example.g130ministore.entity.Product;
import com.example.g130ministore.exception.IncorrectRequestException;
import com.example.g130ministore.repository.ProductRepository;
import com.example.g130ministore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(timeout = 10)
    public void addProducts(List<Product> products) {
        products.forEach(product -> {
            if (product.getPrice() < 0) {
                throw new IncorrectRequestException("Product price cannot be negative");
            }
            productRepository.save(product);
        });
    }

}
