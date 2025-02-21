package com.example.g130ministore.service;

import com.example.g130ministore.entity.Product;
import com.example.g130ministore.repository.ProductRepository;
import com.example.g130ministore.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void priceOfAllProducts() {
        Product product1 = Product.builder().price(1000.0).build();
        Product product2 = Product.builder().price(500.1).build();
        Product product3 = Product.builder().price(600.2).build();
        Product product4 = Product.builder().price(1000.3).build();

        Mockito.when(productRepository.findAll()).thenReturn(List.of(product1, product2, product3, product4));
        double result = productService.priceOfAllProducts();
        Assertions.assertEquals(3100.6, result);
    }
}
