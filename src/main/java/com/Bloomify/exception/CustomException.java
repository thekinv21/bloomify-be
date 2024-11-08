package com.Bloomify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CustomException(ExceptionMessage exception) {
        super(exception.getKey());
        this.status = exception.getStatus();
    }
}
