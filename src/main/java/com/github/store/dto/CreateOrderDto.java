package com.github.store.dto;

import lombok.Data;

@Data
public class CreateOrderDto {
    private Long productId;
    private int amount;
}
//{"name":"Vlad","phone":"123123","orders":[{"amount":13,"productId":"1"},{"amount":12,"productId":"2"}]}