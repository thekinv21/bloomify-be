package com.Bloomify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    /**
     * Default exception ex.
     */
    DEFAULT_EXCEPTION("messages.error.default_message", HttpStatus.BAD_REQUEST),

    /**
     * Already exists exception ex.
     */
    ALREADY_EXISTS_EXCEPTION("messages.error.already_exists_exception", HttpStatus.BAD_REQUEST),

    /**
     * Not found exception ex.
     */
    NOT_FOUND_EXCEPTION("messages.error.not_found_exception", HttpStatus.NOT_FOUND),

    /**
     * User not found exception ex.
     */
    USER_NOT_FOUND_EXCEPTION("messages.error.user_not_found_exception", HttpStatus.NOT_FOUND),

    /**
     * Access denied exception ex.
     */
    ACCESS_DENIED_EXCEPTION("messages.error.access_denied_exception", HttpStatus.UNAUTHORIZED),

    /**
     * Forbidden exception ex.
     */
    FORBIDDEN_EXCEPTION("messages.error.forbidden_exception", HttpStatus.FORBIDDEN),


    /**
     * Wrong credentials exception ex.
     */
    WRONG_CREDENTIALS_EXCEPTION("messages.error.wrong_credentials_exception", HttpStatus.FORBIDDEN),


    /**
     * User already exists exception ex.
     */
    USER_ALREADY_EXISTS_EXCEPTION("messages.error.user_already_exists_exception", HttpStatus.BAD_REQUEST),

    /**
     * Password reset request not found exception ex.
     */
    PASSWORD_RESET_REQUEST_NOT_FOUND_EXCEPTION("messages.error.password_reset_request_code_not_found_exception", HttpStatus.NOT_FOUND),

    /**
     * Password reset request code wrong exception ex.
     */
    PASSWORD_RESET_REQUEST_CODE_WRONG_EXCEPTION("messages.error.password_reset_request_code_wrong_exception", HttpStatus.NOT_FOUND),

    /**
     * Password reset request code expired exception ex.
     */
    PASSWORD_RESET_REQUEST_CODE_EXPIRED_EXCEPTION("messages.error.password_reset_request_code_expired_exception", HttpStatus.NOT_FOUND),


    TOKEN_IS_NOT_VALID_EXCEPTION("messages.error.token_is_not_valid_exception", HttpStatus.UNAUTHORIZED);

    private final String key;
    private final HttpStatus status;

    ExceptionMessage(String key, HttpStatus httpStatus) {
        this.key = key;
        this.status = httpStatus;
    }
}
