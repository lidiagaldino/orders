package com.lidiagaldino.orders.application.services.impl;

import com.lidiagaldino.orders.application.data.input.UserInputData;
import com.lidiagaldino.orders.application.data.output.UserOutputData;
import com.lidiagaldino.orders.application.services.UserService;
import com.lidiagaldino.orders.domain.repositories.UserRepository;
import com.lidiagaldino.orders.domain.services.PasswordCryptography;
import com.lidiagaldino.orders.infraestructure.exception.custom.UserAlreadyExistException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordCryptography passwordCryptography;


    public UserServiceImpl(UserRepository userRepository, PasswordCryptography passwordCryptography) {
        this.userRepository = userRepository;
        this.passwordCryptography = passwordCryptography;
    }

    @Override
    public Uni<UserOutputData> create(UserInputData user) {
        return userRepository.findByEmail(user.email())
                .onItem().ifNotNull()
                .failWith(() -> new UserAlreadyExistException())
                .onItem().ifNull().switchTo(() -> this.passwordCryptography.hash(user.password())
                        .onItem().ifNotNull()
                        .transform(password -> user.toUserEntity(password))
                        .onItem().ifNotNull()
                        .transformToUni(userEntity -> this.userRepository.save(userEntity))).onItem().ifNotNull()
                .transform(savedUser -> UserOutputData.create(savedUser));
    }

    @Override
    public Uni<UserOutputData> findById(String id) {
        return Uni.createFrom()
                .item(id)
                .onItem()
                .ifNotNull()
                .transformToUni(it -> this.userRepository.findById(it))
                .onItem()
                .ifNotNull()
                .transform(it -> UserOutputData.create(it));
    }
}
