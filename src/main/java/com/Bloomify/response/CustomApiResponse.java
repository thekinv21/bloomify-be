package com.Bloomify.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "error",
        "code",
        "message",
        "isPageable",
        "timeStamp",
        "page",
        "pageSize",
        "totalElements",
        "totalPages",
        "isFirst",
        "isLast",
        "isEmpty",
        "content",
})


public class CustomApiResponse {

    @Getter
    public enum CustomResponseConstant {

        SUCCESS(0, "Success"),
        FAILURE(-1, "Failure");

        final int code;
        final String message;

        CustomResponseConstant(int code, String message) {
            this.code = code;
            this.message = message;
        }

    }

    // response fields

    @JsonIgnore private HttpHeaders httpHeaders;
    private HttpStatus status;
    private int code;
    private String message;
    private LocalDateTime timeStamp;

    // Content fields
    private Object content;
    private Boolean isPageable;
    private Integer page;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;
    private Boolean isFirst;
    private Boolean isEmpty;

    // Error field
    private Object error;

    private CustomApiResponse(Builder builder) {
        this.status = builder.status;
        this.httpHeaders = builder.httpHeaders;
        this.code = builder.code;
        this.message = builder.message;
        this.content = builder.content;
        this.isPageable = builder.isPageable;
        this.error = builder.error;
        this.page = builder.page;
        this.pageSize = builder.pageSize;
        this.totalElements = builder.totalElements;
        this.totalPages = builder.totalPages;
        this.isLast = builder.isLast;
        this.isFirst = builder.isFirst;
        this.isEmpty = builder.isEmpty;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(HttpStatus status) {
        return new Builder(status);
    }

    public static class Builder {
        private final HttpHeaders httpHeaders = new HttpHeaders();
        private HttpStatus status;
        private int code;
        private String message;
        private Object content;
        private Boolean isPageable = false;
        private Integer page;
        private Integer pageSize;
        private Long totalElements;
        private Integer totalPages;
        private Boolean isLast;
        private Boolean isFirst;
        private Boolean isEmpty;
        private Object error;

        private Builder() {
            this.status = HttpStatus.OK;
            this.code = CustomResponseConstant.SUCCESS.getCode();
            this.message = CustomResponseConstant.SUCCESS.getMessage();
        }

        private Builder(HttpStatus status) {
            this.status = status;
        }

        public Builder header(String name, String value) {
            this.httpHeaders.add(name, value);
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            this.code = status.value();
            return this;
        }

        public Builder status(int statusCode) {
            this.status = HttpStatus.resolve(statusCode);
            if (this.status == null) {
                throw new IllegalArgumentException("Invalid HTTP status code: " + statusCode);
            }
            this.code = statusCode;
            return this;
        }

        public Builder data(Object object) {
            this.content = object;
            return this;
        }

        public Builder pageableData(Object object) {
            this.isPageable = true;
            Page pageObj = (Page) object;
            this.content = pageObj.getContent();
            this.page = pageObj.getNumber();
            this.pageSize = pageObj.getSize();
            this.totalElements = pageObj.getTotalElements();
            this.totalPages = pageObj.getTotalPages();
            this.isLast = pageObj.isLast();
            this.isFirst = pageObj.isFirst();
            this.isEmpty = pageObj.isEmpty();
            return this;
        }

        public Builder error(Object obj) {
            this.code = CustomResponseConstant.FAILURE.getCode();
            this.message = CustomResponseConstant.FAILURE.getMessage();
            this.error = obj;
            return this;
        }

        public ResponseEntity<CustomApiResponse> build() {
            CustomApiResponse apiResponse = new CustomApiResponse(this);
            apiResponse.timeStamp = LocalDateTime.now();
            return ResponseEntity.status(status).headers(httpHeaders).body(apiResponse);
        }
    }
}
