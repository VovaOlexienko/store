package com.github.store;

import com.github.store.controller.ProductController;
import com.github.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductControllerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    void createOrder_expectSuccess() {
        productController.getProducts();
        verify(productRepository).findAll();
    }
}
