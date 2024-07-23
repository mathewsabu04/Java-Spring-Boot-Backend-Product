package com.example.FinalExamProject;

import org.springframework.http.ResponseEntity;

public interface Query <I , O>{
    ResponseEntity<O> executes(I input);
}
