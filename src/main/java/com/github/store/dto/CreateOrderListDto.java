package com.github.store.dto;

import lombok.Data;

import java.util.List;
@Data
public class CreateOrderListDto {
    private String phone;
    private String name;
    private List<CreateOrderDto> orders;
}
