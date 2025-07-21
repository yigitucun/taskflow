package com.ali.taskflow.shared.exception.globalException;

import com.ali.taskflow.shared.exception.ExceptionDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleGlobalException(GlobalException e){
        ExceptionDetail detail = new ExceptionDetail();
        detail.setStatusCode(e.getHttpStatus().value());
        detail.setMessage(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus().value()).body(detail);
    }
}
