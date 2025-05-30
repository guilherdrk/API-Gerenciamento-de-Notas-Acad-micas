package com.example.gerenciadornotas.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String errorCode;
    private Map<String, String> validationErrors;

    public ErrorDetails(LocalDateTime timestamp, String message, String details, String errorCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
    }

    public ErrorDetails(LocalDateTime timestamp, String message, String details, String errorCode, Map<String, String> validationErrors) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
        this.validationErrors = validationErrors;
    }
}
