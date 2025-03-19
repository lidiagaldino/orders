package com.lidiagaldino.orders.application.services.impl;

import com.lidiagaldino.orders.application.data.input.ProductInputData;
import com.lidiagaldino.orders.application.data.output.ProductOutputData;
import com.lidiagaldino.orders.application.services.ProductService;
import com.lidiagaldino.orders.domain.repositories.ProductRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Uni<ProductOutputData> create(ProductInputData productInputData) {
        return Uni.createFrom()
                .item(productInputData)
                .map(it -> it.toEntity())
                .onItem()
                .ifNotNull()
                .transformToUni(it -> this.productRepository.save(it))
                .onItem()
                .ifNotNull()
                .transform(it -> ProductOutputData.create(it));
    }

    @Override
    public Uni<ProductOutputData> findById(String id) {
        return Uni.createFrom()
                .item(id)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> this.productRepository.findById(id))
                .onItem()
                .ifNotNull()
                .transform(it -> ProductOutputData.create(it));
    }
}
