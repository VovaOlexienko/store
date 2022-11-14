package com.github.store.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     id - Унікальний ідентифікатор користувача
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     name - Ім'я користувача
     */
    private String name;

    /**
     surname - Прізвище користувача
     */
    private String surname;

    /**
     email - Електрона пошта користувача
     */
    private String email;

    /**
     phone - Номер телефону користувача
     */
    private String phone;
}
