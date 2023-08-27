package com.example.rtask.exception;

import org.springframework.http.*;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GitServiceException.class)
    public ResponseEntity<Object> handleApiRequestException(GitServiceException e) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

        ApiRequestError apiException = new ApiRequestError(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );

        return new ResponseEntity<>(apiException, headers, apiException.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers,
                                                                      HttpStatusCode status,
                                                                      WebRequest request) {
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

        ApiRequestError apiException = new ApiRequestError(
                ex.getStatusCode(),
                ex.getMessage()
        );

        return new ResponseEntity<>(apiException, headers, HttpStatus.NOT_ACCEPTABLE);
    }


//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex
//    ) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
//
//        ApiRequestException apiException = new ApiRequestException(
//                ex.getStatusCode(),
//                ex.getMessage()
//        );
//
//        return new ResponseEntity<>(apiException, headers, HttpStatus.NOT_ACCEPTABLE);
//    }

}
