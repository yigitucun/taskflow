package com.ali.taskflow.shared.exception;

import java.time.Instant;

public class ExceptionDetail {
    private String message;
    private final Instant timestamp = Instant.now();
    private int statusCode;

    public ExceptionDetail(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ExceptionDetail() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
