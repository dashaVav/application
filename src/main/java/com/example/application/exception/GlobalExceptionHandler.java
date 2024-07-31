package com.example.application.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ApplicationException> handleTheException(RuntimeException e, HttpStatus status) {
        return new ResponseEntity<>(
                new ApplicationException(e.getMessage(), status.value()),
                status
        );
    }

    @ExceptionHandler(DealException.class)
    public ResponseEntity<ApplicationException> handleDealException(DealException e) {
        return handleTheException(e, HttpStatus.resolve(e.getStatus()));
    }

    @Value("${feign-client.deal-client.base-url}")
    String baseUrl;
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ApplicationException> handleConnectException(ConnectException e) {
        System.out.println(e + baseUrl);
        return handleTheException(new RuntimeException("Connection refused."), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(PrescoringException.class)
    public ResponseEntity<ApplicationException> handleBadRequestsException(RuntimeException e) {
        return handleTheException(e, HttpStatus.BAD_REQUEST);
    }
}
