package com.example.FinalExamProject.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProfanityValidator {
    private static final Logger logger = LoggerFactory.getLogger(ProfanityValidator.class);
    private static final String API_KEY =  "beFCKp3GcfeJKYByu9DTmA==S1fwr6q63ia2qfMS";

    public static boolean hasProfanity(String name, String description){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        try{
            ResponseEntity<ProfanityFilterAPIResponse> nameResponseEnitiy = restTemplate.exchange(
                    "https://api.api-ninjas.com/v1/profanityfilter?text=" + name,
                    HttpMethod.GET,
                    entity,
                    ProfanityFilterAPIResponse.class);

            ResponseEntity<ProfanityFilterAPIResponse> descriptionResponseEnitiy = restTemplate.exchange(
                    "https://api.api-ninjas.com/v1/profanityfilter?text=" + description,
                    HttpMethod.GET,
                    entity,
                    ProfanityFilterAPIResponse.class);

            return (nameResponseEnitiy.getBody().isHas_profanity() || descriptionResponseEnitiy.getBody().isHas_profanity());



        }catch (Exception e){
            throw new ProfanityFilterException();
        }



    }
}
