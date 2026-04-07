package com.felixherder.ftotbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException notFoundException) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .message(notFoundException.getMessage())
                .details("The requested entity was not found")
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
