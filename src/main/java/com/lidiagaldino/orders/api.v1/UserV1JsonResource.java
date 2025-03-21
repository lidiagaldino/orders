package com.lidiagaldino.orders.api.v1;

import com.lidiagaldino.orders.application.data.input.UserInputData;
import com.lidiagaldino.orders.application.data.output.UserOutputData;
import com.lidiagaldino.orders.application.services.UserService;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserV1JsonResource {
    @Inject
    UserService userService;

    @POST
    @WithTransaction
    public Uni<RestResponse<UserOutputData>> store(UserInputData user) {
        return Uni.createFrom()
                .item(user)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> userService.create(it))
                .onItem()
                .ifNotNull()
                .transform(it -> RestResponse.status(Response.Status.CREATED, it));
    }

    @GET
    @Path("/{id}")
    @Authenticated
    @WithSession
    public Uni<RestResponse<UserOutputData>> find(@PathParam("id") String id) {
        return Uni.createFrom()
                .item(id)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> userService.findById(it))
                .onItem()
                .ifNotNull()
                .transform(it -> RestResponse.ok(it));
    }
}
