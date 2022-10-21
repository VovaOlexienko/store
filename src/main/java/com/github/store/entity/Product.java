package com.github.store.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String image;

    private BigDecimal price;

    private int amount;
}
