package com.User.user.Exception;

import org.springframework.http.HttpStatus;

public class InvalidException extends RuntimeException{
    private final HttpStatus httpStatus;

    public InvalidException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
