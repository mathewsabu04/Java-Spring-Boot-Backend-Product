package com.example.FinalExamProject.Exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class ProfanityFilterAPIResponse {
    private String original;
    private String censored;
    private boolean has_profanity;

    // Default constructor
    public ProfanityFilterAPIResponse() {
    }

    // Constructor with parameters
    public ProfanityFilterAPIResponse(boolean has_profanity) {
        this.has_profanity = has_profanity;
    }
}
