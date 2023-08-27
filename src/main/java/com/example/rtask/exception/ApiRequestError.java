package com.example.rtask.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ApiRequestError {

    private final HttpStatusCode status;
    private final String message;

    public ApiRequestError(HttpStatusCode status, String message) {

        this.status = status;
        this.message = message;
    }
}
