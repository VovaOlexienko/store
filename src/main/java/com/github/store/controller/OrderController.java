package com.github.store.controller;

import com.github.store.dto.ClientInfoDto;
import com.github.store.dto.CreateOrderListDto;
import com.github.store.entity.Order;
import com.github.store.entity.Product;
import com.github.store.entity.Status;
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


@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @CrossOrigin
    @PostMapping("/order")
    public void createOrder(@RequestBody CreateOrderListDto cold) {
        User user = userRepository.save(buildUser(cold.getClientInfo()));
        List<Order> orders = cold.getOrders().stream().map(x -> {
            Product p = productRepository.findById(x.getProductId()).orElseThrow();
            return Order.builder().price(p.getPrice()).status(Status.NOT_DONE).amount(x.getAmount()).user(user).product(p).build();
        }).toList();
        orderRepository.saveAll(orders);
    }

    private static User buildUser(ClientInfoDto clientInfo) {
        return User.builder()
                .phone(clientInfo.getPhone())
                .name(clientInfo.getName())
                .surname(clientInfo.getSurname())
                .email(clientInfo.getEmail())
                .build();
    }
}
