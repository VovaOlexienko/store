package com.github.store;

import com.github.store.controller.OrderController;
import com.github.store.dto.CreateOrderDto;
import com.github.store.dto.CreateOrderListDto;
import com.github.store.entity.Order;
import com.github.store.entity.Product;
import com.github.store.entity.User;
import com.github.store.repository.OrderRepository;
import com.github.store.repository.ProductRepository;
import com.github.store.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderControllerTest {

    private final CreateOrderListDto createOrderListDto = getCreateOrderListDto();

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        when(userRepository.save(any())).thenReturn(new User());
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new Product()));
        when(orderRepository.saveAll(any())).thenReturn(List.of(new Order()));
    }

    @Test
    void createOrder_expectSuccess() {
        orderController.createOrder(createOrderListDto);
        verify(userRepository).save(notNull());
        verify(productRepository).findById(notNull());
        verify(orderRepository).saveAll(notNull());
    }

    @Test
    void createOrder_expectExceptionWhenProductIdIsInvalid() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> orderController.createOrder(createOrderListDto));

        verify(userRepository).save(notNull());
        verify(productRepository).findById(notNull());
        verifyNoInteractions(orderRepository);
    }

    @Test
    void createOrder_expectCreatedToOrders() {
        CreateOrderDto createOrderDto = new CreateOrderDto();
        createOrderDto.setProductId(2L);
        createOrderDto.setAmount(4);
        createOrderListDto.getOrders().add(createOrderDto);

        orderController.createOrder(createOrderListDto);

        verify(userRepository).save(notNull());
        verify(productRepository,times(2)).findById(notNull());
        verify(orderRepository).saveAll(notNull());
    }

    private CreateOrderListDto getCreateOrderListDto() {
        CreateOrderDto createOrderDto = new CreateOrderDto();
        createOrderDto.setProductId(1L);
        createOrderDto.setAmount(1);
        List<CreateOrderDto> orders = new ArrayList<>();
        orders.add(createOrderDto);
        CreateOrderListDto createOrderListDto1 = new CreateOrderListDto();
        createOrderListDto1.setName("Vlad");
        createOrderListDto1.setPhone("+380975623811");
        createOrderListDto1.setOrders(orders);
        return createOrderListDto1;
    }
}