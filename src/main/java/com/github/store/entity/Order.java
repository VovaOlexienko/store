package com.github.store.entity;

import com.github.store.constant.Status;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "`order`")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     id - Унікальний ідентифікатор замовлення
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     product - Продукт який було замовлено
     */
    @ManyToOne
    @JoinColumn
    private Product product;

    /**
     user - Користувач, який здійснив замовлення
     */
    @ManyToOne
    @JoinColumn
    private User user;

    /**
     price - Вартість товару на момент замовлення
     */
    private BigDecimal price;

    /**
     amount - Кількість обраних товарів користувачем
     */
    private int amount;

    /**
     status - Статус замовлення
     */
    @Enumerated(EnumType.STRING)
    private Status status;
}
