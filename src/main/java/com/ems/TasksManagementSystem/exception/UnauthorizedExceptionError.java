package com.ems.TasksManagementSystem.exception;

public class UnauthorizedExceptionError extends RuntimeException{

    public UnauthorizedExceptionError() {
    }

    public UnauthorizedExceptionError(String message) {
        super(message);
    }
}
