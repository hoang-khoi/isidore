package com.vietcatholicjp.isidore.domain.exceptions;

public class ClientException extends RuntimeException {

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
