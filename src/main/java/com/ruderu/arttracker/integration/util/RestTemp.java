package com.ruderu.arttracker.integration.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

public class RestTemp {

    private final static RestTemplate restTemplate = new RestTemplate();

    public <T> ResponseEntity<T> getRest(URL url, HttpEntity<String> request, Class<T> responseType) {
        return restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                request,
                responseType);
    }

    public <T> T  getBody(URL url, Class<T> responseType) {
        return getBody(url, null, responseType);
    }

    public <T> T getBody(URL url, HttpEntity<String> request, Class<T> responseType) {
        T response = getRest(url, request, responseType).getBody();
        if (response == null) {
            throw new IllegalStateException("Ничего не найдено");
        }
        return response;
    }


}
