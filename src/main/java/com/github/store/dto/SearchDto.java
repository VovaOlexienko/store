package com.github.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchDto {

    @JsonProperty("searchOptions")
    private String searchOptions;
}
