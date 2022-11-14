package com.github.store.controller;

import com.github.store.dto.ClientInfoDto;
import com.github.store.dto.CreateOrderDto;
import com.github.store.dto.CreateOrderListDto;
import com.github.store.entity.Order;
import com.github.store.entity.Product;
import com.github.store.constant.Status;
import com.github.store.entity.User;
import com.github.store.repository.OrderRepository;
import com.github.store.repository.ProductRepository;
import com.github.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контролер для роботи з замовленнями
 * @author Rat Industries
 */
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * Створення замовлення
     * @param dto Список продуктів для замовлення і інформація для ідентифікації клієнта
     * @since 1.0
     */
    @CrossOrigin
    @PostMapping("/order")
    public void createOrder(@RequestBody CreateOrderListDto dto) {
        User user = userRepository.save(buildUser(dto.getClientInfo()));
        List<Order> orders = dto.getOrders().stream()
                .map(x -> buildOrder(user, x, productRepository.findById(x.getProductId()).orElseThrow()))
                .toList();
        orderRepository.saveAll(orders);
    }

    private Order buildOrder(User user, CreateOrderDto x, Product product) {
        return Order.builder()
                .price(product.getPrice())
                .status(Status.NOT_DONE)
                .amount(x.getAmount())
                .user(user)
                .product(product)
                .build();
    }

    private User buildUser(ClientInfoDto clientInfo) {
        return User.builder()
                .phone(clientInfo.getPhone())
                .name(clientInfo.getName())
                .surname(clientInfo.getSurname())
                .email(clientInfo.getEmail())
                .build();
    }
}
