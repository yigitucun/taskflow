package com.ali.taskflow.shared.exception.globalException;

import com.ali.taskflow.shared.exception.ExceptionDetail;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleGlobalException(GlobalException e){
        ExceptionDetail detail = new ExceptionDetail();
        detail.setStatusCode(e.getHttpStatus().value());
        detail.setMessage(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus().value()).body(detail);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleEnumParseError(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException cause && cause.getTargetType().isEnum()) {
            String fieldName = cause.getPath().get(0).getFieldName();
            Object invalidValue = cause.getValue();
            Object[] allowedValues = cause.getTargetType().getEnumConstants();
            ExceptionDetail detail = new ExceptionDetail();
            detail.setStatusCode(400);
            detail.setMessage("Geçersiz değer: '" + invalidValue + "' (Alan: " + fieldName + "). " +
                    "Geçerli değerler: " + Arrays.toString(allowedValues));
            return ResponseEntity.status(400).body(detail);
        }
        return ResponseEntity.badRequest().body("İstek hatalı veya eksik.");
    }
}
