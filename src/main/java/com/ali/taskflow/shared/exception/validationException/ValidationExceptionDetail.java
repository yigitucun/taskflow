package com.ali.taskflow.shared.exception.validationException;

import java.time.Instant;
import java.util.HashMap;

public class ValidationExceptionDetail {
    private HashMap<String,String> validationErrors;
    private final Instant timestamp = Instant.now();
    private final static int statusCode = 400;

    public ValidationExceptionDetail(HashMap<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationExceptionDetail() {
    }

    public HashMap<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(HashMap<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
