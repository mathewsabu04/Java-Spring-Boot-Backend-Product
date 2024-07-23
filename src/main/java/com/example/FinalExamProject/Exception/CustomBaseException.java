package com.example.FinalExamProject.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public class CustomBaseException extends RuntimeException{
    private HttpStatus status;
    private SimpleResponse simpleResponse;
}
