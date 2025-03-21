package com.lidiagaldino.orders.domain.repositories;

import com.lidiagaldino.orders.domain.entities.UserEntity;
import io.smallrye.mutiny.Uni;

public interface UserRepository {
    Uni<UserEntity> save(UserEntity user);
    Uni<UserEntity> findById(String id);
    Uni<UserEntity> findByEmail(String id);
}
