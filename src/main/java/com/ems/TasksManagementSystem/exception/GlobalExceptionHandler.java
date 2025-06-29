package com.ems.TasksManagementSystem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(UnauthorizedExceptionError.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedExceptionError ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, ex);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(RecordNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(DuplicatedErrorException.class)
    public ResponseEntity<ErrorResponse> duplicatedError(DuplicatedErrorException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse("Validation failed", details);
        logger.error("Validation Error: {}", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse error = new ErrorResponse("Internal Server Error", Arrays.asList(ex.getMessage()));
        logger.error("Unexpected Error: ", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), Arrays.asList(ex.getMessage()));
        logger.error("Error {}: {}", status.value(), ex.getMessage());
        return new ResponseEntity<>(error, status);
    }
}
