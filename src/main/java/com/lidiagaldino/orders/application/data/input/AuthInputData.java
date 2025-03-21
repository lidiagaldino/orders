package com.lidiagaldino.orders.application.data.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthInputData(
        @JsonProperty("email")
        String email,
        @JsonProperty("password")
        String password
) {
}