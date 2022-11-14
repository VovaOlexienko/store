package com.github.store.controller;

import com.github.store.entity.Product;
import com.github.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @CrossOrigin
    @GetMapping("/product")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody String s){
        return List.of(Product.builder().description("тестируем поиск").build());
    }
}
