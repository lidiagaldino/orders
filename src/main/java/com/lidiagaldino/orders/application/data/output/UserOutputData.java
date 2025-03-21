package com.lidiagaldino.orders.application.data.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lidiagaldino.orders.domain.entities.UserEntity;

public record UserOutputData(
        @JsonProperty("name")
        String name,
        @JsonProperty("email")
        String email
) {
        public static UserOutputData create(UserEntity entity) {
                return new UserOutputData(
                        entity.getName(),
                        entity.getEmail()
                );
        }
}
