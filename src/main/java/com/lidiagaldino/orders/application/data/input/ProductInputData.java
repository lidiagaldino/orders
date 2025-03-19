package com.lidiagaldino.orders.application.data.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lidiagaldino.orders.domain.entities.ProductEntity;

public record ProductInputData(
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("price")
        Double price
) {
    public ProductEntity toEntity() {
        return new ProductEntity(
                this.name,
                this.description,
                this.price
        );
    }
}
