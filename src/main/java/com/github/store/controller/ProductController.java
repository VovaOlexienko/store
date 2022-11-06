package com.github.store.controller;

import com.github.store.entity.Product;
import com.github.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
