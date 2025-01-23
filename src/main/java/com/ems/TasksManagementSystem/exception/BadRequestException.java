package com.ems.TasksManagementSystem.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
