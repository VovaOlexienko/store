package com.github.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderListDto {

    @JsonProperty("clientInfo")
    private ClientInfoDto clientInfo;
    @JsonProperty("products")
    private List<CreateOrderDto> orders;
}
