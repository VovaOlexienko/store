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

    /**
     id - Унікальний ідентифікатор продукту
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     description - Назва продукту
     */
    private String description;

    /**
     image - Посилання на картинку продукту
     */
    private String image;

    /**
     price - Ціна продукту
     */
    private BigDecimal price;

    /**
     amount - Кількість доступних для замовлення продуктів
     */
    private int amount;
}
