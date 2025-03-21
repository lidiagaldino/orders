package com.lidiagaldino.orders.api.v1;

import com.lidiagaldino.orders.application.data.input.AuthInputData;
import com.lidiagaldino.orders.application.data.output.AuthOutputData;
import com.lidiagaldino.orders.application.services.SessionService;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionV1JsonResource {
    @Inject
    SessionService sessionService;

    @POST
    @WithTransaction
    public Uni<RestResponse<AuthOutputData>> login(AuthInputData user) {
        return Uni.createFrom()
                .item(user)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> sessionService.login(it))
                .onItem()
                .ifNotNull()
                .transform(it -> RestResponse.ok(it));
    }
}