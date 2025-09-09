package com.pizzeria.admin.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MenuItemDTO {

	private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String imageUrl;

    
}
