package com.example.FinalExamProject;

import org.springframework.http.ResponseEntity;

public interface Command <I, O>
{
    ResponseEntity<O> executes(I input);
}