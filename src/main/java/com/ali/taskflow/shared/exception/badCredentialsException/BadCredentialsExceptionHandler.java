package com.ali.taskflow.shared.exception.badCredentialsException;

import com.ali.taskflow.shared.exception.ExceptionDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadCredentialsExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e){
        ExceptionDetail detail = new ExceptionDetail();
        detail.setMessage("Username or password wrong");
        detail.setStatusCode(401);
        return ResponseEntity.status(401).body(detail);
    }
}
