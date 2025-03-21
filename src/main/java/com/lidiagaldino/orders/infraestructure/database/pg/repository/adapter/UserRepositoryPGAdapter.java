package com.lidiagaldino.orders.infraestructure.database.pg.repository.adapter;

import com.lidiagaldino.orders.domain.entities.UserEntity;
import com.lidiagaldino.orders.domain.repositories.UserRepository;
import com.lidiagaldino.orders.infraestructure.database.pg.entity.UserEntityModel;
import com.lidiagaldino.orders.infraestructure.database.pg.repository.UserPGService;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;


@ApplicationScoped
public class UserRepositoryPGAdapter implements UserRepository {
    private final UserPGService userPgService;

    public UserRepositoryPGAdapter(UserPGService userRepository) {
        this.userPgService = userRepository;
    }

    @Override
    @WithSpan("UserRepositorySave")
    public Uni<UserEntity> save(UserEntity user) {
        return Uni.createFrom()
                .item(user)
                .onItem()
                .ifNotNull()
                .transform(it -> UserEntityModel.from(it))
                .onItem()
                .transformToUni(it -> userPgService.save(it))
                .map(it -> it.toEntity());
    }

    @Override
    @WithSpan("UserRepositoryFindByEmail")
    public Uni<UserEntity> findByEmail(String email) {
        return Uni.createFrom()
                .item(email)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> userPgService.findByEmail(it))
                .onItem()
                .ifNotNull()
                .transform(it -> it.toEntity());
    }

    @Override
    @WithSpan("UserRepositoryFindById")
    public Uni<UserEntity> findById(String id) {
        return Uni.createFrom()
                .item(id)
                .onItem()
                .ifNotNull()
                .transform(it -> UUID.fromString(id))
                .onItem()
                .ifNotNull()
                .transformToUni(it -> userPgService.findById(it))
                .onItem()
                .ifNotNull()
                .transform(it -> it.toEntity());
    }
}
