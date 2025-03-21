package com.lidiagaldino.orders.domain.services;

import com.lidiagaldino.orders.domain.entities.UserEntity;
import io.smallrye.mutiny.Uni;

public interface AuthToken {
    Uni<String> generateToken(UserEntity user);
}