package com.ems.TasksManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandller {

    @ExceptionHandler({RecordNotFoundException.class})
    public ResponseEntity<?> notFoundException(RecordNotFoundException ex){
        ErrorResponse error= new ErrorResponse(ex.getMessage(), Arrays.asList(ex.getMessage()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler({DuplicatedErrorException.class})
    public ResponseEntity<?> duplicatedError(DuplicatedErrorException ex){
        ErrorResponse error= new ErrorResponse(ex.getMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);

    }
    
}
