package com.github.brzozas.wiremock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class WireMockSandbox {

    private RestTemplate httpClient;

    public WireMockSandbox(RestTemplate httpClient) {
        this.httpClient = httpClient;
    }

    public void sendJsonPostRequest(String url, RegisterRequestDTO requestBody) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        String requestAsString = jsonMapper.writeValueAsString(requestBody);
        String result = httpClient.postForObject(url, requestAsString, String.class);
    }
}
