package com.lidiagaldino.orders.infraestructure.database.pg.entity;

import com.lidiagaldino.orders.domain.entities.ProductEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntityModel extends PanacheEntityBase {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;
    private String description;
    private Double price;

    public ProductEntityModel(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static ProductEntityModel from(ProductEntity productEntity) {
        return new ProductEntityModel(
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice()
        );
    }

    public ProductEntity to() {
        return new ProductEntity(
                this.id.toString(),
                this.name,
                this.description,
                this.price
        );
    }
}
