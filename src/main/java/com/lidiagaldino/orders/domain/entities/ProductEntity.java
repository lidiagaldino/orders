package com.lidiagaldino.orders.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductEntity {
    private String id;
    private String name;
    private String description;
    private Double price;

    public ProductEntity(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
