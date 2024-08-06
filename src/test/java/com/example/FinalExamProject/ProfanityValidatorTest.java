package com.example.FinalExamProject;

import com.example.FinalExamProject.Exception.ProfanityFilterAPIResponse;
import com.example.FinalExamProject.Exception.ProfanityFilterException;
import com.example.FinalExamProject.Exception.ProfanityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes =  FinalExamProjectApplication.class)
public class ProfanityValidatorTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ProfanityValidator profanityValidator;


    @BeforeEach
    void setup(){
        this.profanityValidator = new ProfanityValidator(restTemplate);

    }

    @Test
    void testHasProfanity_returnsTrue() {
        ProfanityFilterAPIResponse response = new ProfanityFilterAPIResponse(true);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(ProfanityFilterAPIResponse.class))
        ).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        assertTrue(profanityValidator.hasProfanity("testName", "testDescription"));
    }

    @Test
    void testHasProfanity_returnsFalse() {
        ProfanityFilterAPIResponse response = new ProfanityFilterAPIResponse(false);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(ProfanityFilterAPIResponse.class))
        ).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        assertFalse(profanityValidator.hasProfanity("testName", "testDescription"));
    }

    @Test
    void testHasProfanityValidator_throwException() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(ProfanityFilterAPIResponse.class)))
                .thenThrow(new RuntimeException());


        assertThrows(ProfanityFilterException.class, () -> profanityValidator.hasProfanity("testName", "testDescription"));
    }

}
