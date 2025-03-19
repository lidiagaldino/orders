package com.lidiagaldino.orders.domain.repositories;

import com.lidiagaldino.orders.domain.entities.ProductEntity;
import io.smallrye.mutiny.Uni;

public interface ProductRepository {
    Uni<ProductEntity> save(ProductEntity product);
    Uni<ProductEntity> delete(String id);
    Uni<ProductEntity> findById(String id);
}
