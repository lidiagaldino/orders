package com.lidiagaldino.orders.infraestructure.exception.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lidiagaldino.orders.infraestructure.exception.BaseCustomException;

public record ErrorDTO(
        @JsonProperty("message")
        String message,
        @JsonProperty("cause")
        String cause
) {
    public ErrorDTO(BaseCustomException baseCustomException) {
        this(baseCustomException.getMessage(), baseCustomException.getErrorCode());
    }
}