package com.lidiagaldino.orders.infraestructure.exception.custom;

import com.lidiagaldino.orders.infraestructure.exception.BaseCustomException;

public class ForbiddenException extends BaseCustomException {
    public static final String DEFAULT_MESSAGE = "Forbidden";
    public static final String ERROR_MESSAGE = "Forbidden";
    public static final Integer ERROR_CODE = 403;

    public ForbiddenException() {
        super(DEFAULT_MESSAGE, ERROR_MESSAGE, ERROR_CODE);
    }
}
