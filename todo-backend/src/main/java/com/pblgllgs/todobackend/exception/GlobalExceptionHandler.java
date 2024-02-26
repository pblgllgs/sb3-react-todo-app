package com.pblgllgs.todobackend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        ErrorResponse ErrorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(ErrorResponse, HttpStatus.NOT_FOUND);
    }


}
