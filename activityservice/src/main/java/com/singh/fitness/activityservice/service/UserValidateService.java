package com.singh.fitness.activityservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidateService {

    private final WebClient userServiceWebClient;

    public Boolean validateUser(String userId){
        log.info("Calling User Service for: {}", userId);
        try{
            return userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
}
