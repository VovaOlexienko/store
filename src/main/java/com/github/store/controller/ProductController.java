package com.github.store.controller;

import com.github.store.dto.ProductDto;
import com.github.store.dto.SearchDto;
import com.github.store.entity.Product;
import com.github.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контролер для роботи з продуктами
 * @author Rat Industries
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    /**
     * Отримання всіх продуктів
     * @return Список продуктів доступних для замовлення
     * @since 1.0
     */
    @CrossOrigin
    @GetMapping("/product")
    public List<ProductDto> getProducts() {
        return buildProducts(productRepository.findAll());
    }

    /**
     * Пошук продуктів за назвою
     * @return Список знайдених продуктів доступних для замовлення
     * @since 1.0
     */
    @CrossOrigin
    @PostMapping("/search")
    public List<ProductDto> searchProducts(@RequestBody SearchDto searchDto) {
        return buildProducts(productRepository.findProductsByDescriptionContaining(searchDto.getSearchOptions()));
    }

    private List<ProductDto> buildProducts(List<Product> products) {
        return products.stream()
                .map(this::buildProduct)
                .toList();
    }

    private ProductDto buildProduct(Product product) {
        return ProductDto.builder().id(product.getId())
                .price(product.getPrice())
                .image(product.getImage())
                .description(product.getDescription())
                .amount(product.getAmount())
                .build();
    }
}
