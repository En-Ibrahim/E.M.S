package com.ems.TasksManagementSystem.exception;

public class DuplicatedErrorException extends RuntimeException{

    public DuplicatedErrorException() {
    }

    public DuplicatedErrorException(String message) {
        super(message);
    }


}
