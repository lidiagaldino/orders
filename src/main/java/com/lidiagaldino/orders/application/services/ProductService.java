package com.lidiagaldino.orders.application.services;

import com.lidiagaldino.orders.application.data.input.ProductInputData;
import com.lidiagaldino.orders.application.data.output.ProductOutputData;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface ProductService {
    Uni<ProductOutputData> create(ProductInputData productInputData);
    Uni<ProductOutputData> findById(String id);
    Multi<ProductOutputData> filterByName(String name);
}
