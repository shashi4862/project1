package com.pizzeria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category; // Pizza, Sides, Beverages, Combo

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Stock quantity is required")
    private Integer stockQuantity;

    private String imagePath; // store uploaded image path
}
