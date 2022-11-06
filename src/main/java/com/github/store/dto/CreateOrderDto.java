package com.github.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateOrderDto {

    @JsonProperty("id")
    private Long productId;
    @JsonProperty("quantity")
    private int amount;
}