package com.lidiagaldino.orders.infraestructure.database.pg.repository;

import com.lidiagaldino.orders.infraestructure.database.pg.entity.UserEntityModel;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class UserPGService implements PanacheRepository<UserEntityModel> {
    public Uni<UserEntityModel> findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Uni<UserEntityModel> save(UserEntityModel user) {
        return persist(user);
    }

    public Uni<UserEntityModel> findById(UUID id) {
        return find("id", id).firstResult();
    }
}
