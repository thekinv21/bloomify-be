package com.Bloomify.exception;

import com.Bloomify.response.CustomApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j

public class GlobalBaseException extends BaseException {

    @ExceptionHandler(CustomException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<CustomApiResponse> generalException(CustomException exception) {
        return buildErrorResponse(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<CustomApiResponse> handleMethodArgumentNotValidEx(
            MethodArgumentNotValidException ex, WebRequest request) {
        return getMapResponseEntity(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<CustomApiResponse> handleConstraintViolationEx(
            MethodArgumentNotValidException ex, WebRequest request) {
        return getMapResponseEntity(ex);
    }

    private ResponseEntity<CustomApiResponse> getMapResponseEntity(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        x -> {
                            String errorMessage = x.getDefaultMessage();
                            if (x instanceof FieldError) {
                                String fieldName = ((FieldError) x).getField();
                                errors.put(fieldName, errorMessage);
                            } else {
                                String objectName = x.getObjectName();
                                errors.put(objectName, errorMessage);
                            }
                        });
        return buildErrorResponse(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientRequestException.class)
    public ResponseEntity<CustomApiResponse> generalException(ClientRequestException exception) {
        return buildErrorResponse(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomApiResponse> handleAllException(Exception ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<CustomApiResponse> handleHttpMessageNotReadableException(
            Exception ex, WebRequest request) {
        return buildErrorResponse("Body is missing!", HttpStatus.BAD_REQUEST);
    }
}
