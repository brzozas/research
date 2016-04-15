package com.github.brzozas.wiremock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.brzozas.JsonMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class WireMockSandbox {

    private static HttpHeaders jsonHeaders = new HttpHeaders();

    static{
        jsonHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
    }

    private RestTemplate httpClient;

    public WireMockSandbox(RestTemplate httpClient) {
        this.httpClient = httpClient;;
    }

    public RegisterResponseDTO sendJsonPostRequest(String url, RegisterRequestDTO requestBody) throws JsonProcessingException {
        HttpEntity<String> httpEntity = new HttpEntity<String>(JsonMapper.mapToString(requestBody), jsonHeaders);
        String result = httpClient.postForObject(url, httpEntity, String.class);
        return JsonMapper.mapToObject(result, RegisterResponseDTO.class);
    }

}
