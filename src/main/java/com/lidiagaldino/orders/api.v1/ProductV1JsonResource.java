package com.lidiagaldino.orders.api.v1;

import com.lidiagaldino.orders.application.data.input.ProductInputData;
import com.lidiagaldino.orders.application.data.output.ProductOutputData;
import com.lidiagaldino.orders.application.services.ProductService;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class ProductV1JsonResource {
    @Inject
    ProductService productService;

    @POST
    @WithTransaction
    public Uni<RestResponse<ProductOutputData>> save(ProductInputData user) {
        return Uni.createFrom()
                .item(user)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> this.productService.create(it))
                .onItem()
                .ifNotNull()
                .transform(it -> RestResponse.status(Response.Status.CREATED, it));
    }

    @GET
    @Path("/{id}")
    @WithSession
    public Uni<RestResponse<ProductOutputData>> find(@PathParam("id") String id) {
        return Uni.createFrom()
                .item(id)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> this.productService.findById(it))
                .onItem()
                .ifNotNull()
                .transform(it -> RestResponse.ok(it));
    }
}
