package com.Bloomify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientRequestException extends RuntimeException {

    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * Instantiates a new Client request exception.
     *
     * @param message the message
     * @param status the status
     */
    public ClientRequestException(String message, HttpStatus status) {
        super(message);
    }
}

