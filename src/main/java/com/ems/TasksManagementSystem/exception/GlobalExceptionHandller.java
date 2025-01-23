package com.ems.TasksManagementSystem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandller {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandller.class);


    // Handle Bad Request Exception (400)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        ErrorResponse error= new ErrorResponse(ex.getMessage(), Arrays.asList(ex.getMessage()));
        logger.error("Error :{}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error 400: " + ex.getMessage());
    }

    // Handle Unauthorized Exception (401)
    @ExceptionHandler(UnauthorizedExceptionError.class)
    public ResponseEntity<String> handleUnauthorized(UnauthorizedExceptionError ex) {
        ErrorResponse error= new ErrorResponse(ex.getMessage(), Arrays.asList(ex.getMessage()));
        logger.error("Error :{}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error 401: " + ex.getMessage());
    }

    // Handle Forbidden Exception (403)
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> handleForbidden(ForbiddenException ex) {
        ErrorResponse error= new ErrorResponse(ex.getMessage(), Arrays.asList(ex.getMessage()));
        logger.error("Error :{}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Error 403: " + ex.getMessage());
    }

    // Handle Not Found Exception (404)
    @ExceptionHandler({RecordNotFoundException.class})
    public ResponseEntity<?> notFoundException(RecordNotFoundException ex){
        ErrorResponse error= new ErrorResponse(ex.getMessage(), Arrays.asList(ex.getMessage()));
        logger.error("Error :{}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler({DuplicatedErrorException.class})
    public ResponseEntity<?> duplicatedError(DuplicatedErrorException ex){
        ErrorResponse error= new ErrorResponse(ex.getMessage(), Arrays.asList(ex.getMessage()));
        logger.error("Error :{}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);

    }
    
}
