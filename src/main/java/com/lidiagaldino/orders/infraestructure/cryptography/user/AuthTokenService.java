package com.lidiagaldino.orders.infraestructure.cryptography.user;

import com.lidiagaldino.orders.domain.entities.UserEntity;
import com.lidiagaldino.orders.domain.services.AuthToken;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class AuthTokenService implements AuthToken {
    @Override
    @WithSpan("jwtGenerateToken")
    public Uni<String> generateToken(UserEntity user) {
        return Uni.createFrom()
                .item(user)
                .onItem()
                .ifNotNull()
                .transform(it -> Jwt.issuer("orders-backend")
                        .upn(it.getEmail())
                        .subject(it.getId())
                        .groups("user")
                        .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                        .sign());
    }
}