package com.vietcatholicjp.isidore.domain.exceptions;

public class EmailExistedException extends ClientException {

    public EmailExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
