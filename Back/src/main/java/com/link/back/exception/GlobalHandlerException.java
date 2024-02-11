package com.link.back.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<?> handleGlobalException() {
        return ResponseEntity.status(401).build();
    }
}
