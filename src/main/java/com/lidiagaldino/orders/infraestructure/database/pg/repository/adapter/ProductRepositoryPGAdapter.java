package com.lidiagaldino.orders.infraestructure.database.pg.repository.adapter;

import com.lidiagaldino.orders.domain.entities.ProductEntity;
import com.lidiagaldino.orders.domain.repositories.ProductRepository;
import com.lidiagaldino.orders.infraestructure.database.pg.entity.ProductEntityModel;
import com.lidiagaldino.orders.infraestructure.database.pg.repository.ProductPGService;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class ProductRepositoryPGAdapter implements ProductRepository {

    private final ProductPGService productPGService;

    public ProductRepositoryPGAdapter(ProductPGService productPGService) {
        this.productPGService = productPGService;
    }

    @Override
    @WithSpan("ProductRepositorySave")
    public Uni<ProductEntity> save(ProductEntity product) {
        return Uni.createFrom()
                .item(product)
                .onItem()
                .ifNotNull()
                .transform(it -> ProductEntityModel.from(it))
                .onItem()
                .transformToUni(it -> productPGService.save(it))
                .map(it -> it.to());
    }

    @Override
    @WithSpan("ProductRepositoryDelete")
    public Uni<ProductEntity> delete(String id) {
        return null;
    }

    @Override
    @WithSpan("ProductRepositoryFindById")
    public Uni<ProductEntity> findById(String id) {
        return Uni.createFrom()
                .item(id)
                .onItem()
                .ifNotNull()
                .transform(it -> UUID.fromString(id))
                .onItem()
                .ifNotNull()
                .transformToUni(it -> productPGService.findById(it))
                .onItem()
                .ifNotNull()
                .transform(it -> it.to());
    }
}
