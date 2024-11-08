package com.Bloomify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {

    DEFAULT_EXCEPTION("Something went wrong.", HttpStatus.BAD_REQUEST),

    ALREADY_EXISTS_EXCEPTION("Record already exists.", HttpStatus.BAD_REQUEST),

    NOT_FOUND_EXCEPTION("Resource not found.", HttpStatus.NOT_FOUND),

    USER_NOT_FOUND_EXCEPTION("User not found.", HttpStatus.NOT_FOUND),

    ACCESS_DENIED_EXCEPTION("Please log in.", HttpStatus.UNAUTHORIZED),

    FORBIDDEN_EXCEPTION("Permission denied.", HttpStatus.FORBIDDEN),

    WRONG_CREDENTIALS_EXCEPTION("Wrong username or password.", HttpStatus.FORBIDDEN),

    USER_ALREADY_EXISTS_EXCEPTION("User already exists.", HttpStatus.BAD_REQUEST),

    PASSWORD_RESET_REQUEST_NOT_FOUND_EXCEPTION("Reset request not found.", HttpStatus.NOT_FOUND),

    PASSWORD_RESET_REQUEST_CODE_WRONG_EXCEPTION("Reset code incorrect.", HttpStatus.NOT_FOUND),

    PASSWORD_RESET_REQUEST_CODE_EXPIRED_EXCEPTION("Reset code expired. Request new one.", HttpStatus.NOT_FOUND),

    TOKEN_IS_NOT_VALID_EXCEPTION("Invalid token. Please log in again.", HttpStatus.UNAUTHORIZED);


    private final String key;
    private final HttpStatus status;

    ExceptionMessage(String key, HttpStatus httpStatus) {
        this.key = key;
        this.status = httpStatus;
    }
}
