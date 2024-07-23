package com.example.FinalExamProject.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<SimpleResponse> handleException(CustomBaseException customBaseException){
        return ResponseEntity.status(customBaseException.getStatus()).body(customBaseException.getSimpleResponse());
    }
}
