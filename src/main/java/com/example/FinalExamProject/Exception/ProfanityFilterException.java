package com.example.FinalExamProject.Exception;

import org.springframework.http.HttpStatus;

public class ProfanityFilterException extends  CustomBaseException{
    public ProfanityFilterException() {
        super(HttpStatus.NOT_FOUND, new SimpleResponse(ErrorMessage.PROFANITY_FILTER_DOWN.getMessage()));
    }
}
