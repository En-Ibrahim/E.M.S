package com.ems.TasksManagementSystem.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private boolean success;
    private String message;
     private LocalDateTime date;
     private List<String> details;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
        this.success=Boolean.FALSE;
        this.date= LocalDateTime.now();
    }
}
