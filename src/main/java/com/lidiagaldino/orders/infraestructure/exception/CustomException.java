package com.lidiagaldino.orders.infraestructure.exception;

public interface CustomException {

    Integer getHttpStatusCode();
    String getErrorCode();
}