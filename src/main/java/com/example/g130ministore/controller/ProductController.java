package com.example.g130ministore.controller;

import com.example.g130ministore.entity.Product;
import com.example.g130ministore.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
@Tag(name = "ProductController", description = "API для управления товарами")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/total-price")
    public ResponseEntity<Double> totalPrice() {
        double total = productService.priceOfAllProducts();
        return ResponseEntity.ok(total);
    }

    @PostMapping("/list")
    public ResponseEntity<Void> addProducts(@RequestBody List<Product> products) {
        productService.addProducts(products);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
