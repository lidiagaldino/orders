package com.lidiagaldino.orders.application.data.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lidiagaldino.orders.domain.entities.UserEntity;

public record UserInputData(
        @JsonProperty("name")
        String name,
        @JsonProperty("email")
        String email,
        @JsonProperty("password")
        String password
) {
        public UserEntity toUserEntity(String password) {
                return new UserEntity(
                        this.name,
                        this.email,
                        password
                );
        }
}
