package com.lidiagaldino.orders.application.services.impl;

import com.lidiagaldino.orders.application.data.input.AuthInputData;
import com.lidiagaldino.orders.application.data.output.AuthOutputData;
import com.lidiagaldino.orders.application.services.SessionService;
import com.lidiagaldino.orders.domain.repositories.UserRepository;
import com.lidiagaldino.orders.domain.services.AuthToken;
import com.lidiagaldino.orders.domain.services.PasswordCryptography;
import com.lidiagaldino.orders.infraestructure.exception.custom.ForbiddenException;
import com.lidiagaldino.orders.infraestructure.exception.custom.UserAlreadyExistException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SessionServiceImpl implements SessionService {

    private final UserRepository userRepository;
    private final PasswordCryptography passwordCryptography;
    private final AuthToken authToken;

    public SessionServiceImpl(UserRepository userRepository, PasswordCryptography passwordCryptography, AuthToken authToken) {
        this.userRepository = userRepository;
        this.passwordCryptography = passwordCryptography;
        this.authToken = authToken;
    }

    @Override
    public Uni<AuthOutputData> login(AuthInputData data) {
        return Uni.createFrom()
                .item(data)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> userRepository.findByEmail(data.email()))
                .onItem()
                .ifNotNull()
                .transformToUni(it -> this.passwordCryptography.verify(data.password(), it.getPassword())
                        .onItem()
                        .transformToUni(result -> {
                            if(result) return Uni.createFrom().item(it);
                            return Uni.createFrom().failure(() -> new ForbiddenException());
                        })
                )
                .onItem()
                .ifNotNull()
                .transformToUni(it -> authToken.generateToken(it))
                .map(it -> new AuthOutputData(it));
    }
}