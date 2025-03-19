package com.lidiagaldino.orders.application.data.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lidiagaldino.orders.domain.entities.ProductEntity;

public record ProductOutputData(
        @JsonProperty("id")
        String id,
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("price")
        Double price
) {
    public static ProductOutputData create(ProductEntity entity) {
        return new ProductOutputData(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
