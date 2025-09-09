package com.pizzeria.order.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long menuItemId;
    private int quantity;
}
