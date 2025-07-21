package com.ali.taskflow.shared.exception.validationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
        ValidationExceptionDetail detail = new ValidationExceptionDetail();
        detail.setValidationErrors(new HashMap<>());
        for (FieldError error:e.getBindingResult().getFieldErrors()){
            detail.getValidationErrors().put(error.getField(),error.getDefaultMessage());
        }
        return ResponseEntity.status(400).body(detail);
    }
}


