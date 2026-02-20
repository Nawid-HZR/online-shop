package com.nawid.onlineshop.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public UserAlreadyExistsException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserAlreadyExistsException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public UserAlreadyExistsException(String message, Throwable cause, HttpStatus status, String message1) {
        super(message, cause);
        this.status = status;
        this.message = message1;
    }

    public UserAlreadyExistsException(Throwable cause, HttpStatus status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }

    public UserAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
