package com.Bloomify.exception;

import com.Bloomify.response.CustomApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseException {

    protected final ResponseEntity<CustomApiResponse> buildErrorResponse(Object error, HttpStatus status) {
        return CustomApiResponse.builder().error(error).status(status).build();
    }
}
