package com.felixherder.ftotbe.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleNotFoundException_ShouldReturnErrorResponse() {
        NotFoundException exception = new NotFoundException("Entity not found");

        ErrorResponse response = globalExceptionHandler.handleNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Entity not found", response.getMessage());
        assertEquals("The requested entity was not found", response.getErrors().get("exception"));
        assertNotNull(response.getTimestamp());
    }

    @Test
    void handleUsernameConflictException_ShouldReturnErrorResponse() {
        UsernameConflictException exception = new UsernameConflictException("Username already exists");

        ErrorResponse response = globalExceptionHandler.handleUsernameConflictException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertNotNull(response.getTimestamp());
    }

    @Test
    void handleValidationExceptions_ShouldReturnErrorResponse() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field1", "defaultMessage");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        ErrorResponse response = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Validation failed!", response.getMessage());
        assertEquals("defaultMessage", response.getErrors().get("field1"));
        assertNotNull(response.getTimestamp());
    }
}
