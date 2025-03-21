package com.lidiagaldino.orders.infraestructure.database.pg.repository;

import com.lidiagaldino.orders.infraestructure.database.pg.entity.ProductEntityModel;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class ProductPGService implements PanacheRepository<ProductEntityModel> {
    public Uni<ProductEntityModel> save(ProductEntityModel product) {
        return persist(product);
    }

    public Uni<ProductEntityModel> findById(UUID id) {
        return find("id", id).firstResult();
    }

    public Multi<ProductEntityModel> findByName(String name) {
        return list("LOWER(name) LIKE LOWER(:name)", Parameters.with("name", "%" + name + "%"))
                .onItem()
                .ifNotNull()
                .transformToMulti(it -> Multi.createFrom().iterable(it));
    }
}
