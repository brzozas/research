package com.github.brzozas.wiremock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.brzozas.JsonMapper;
import com.github.tomakehurst.wiremock.client.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockSandboxTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().port(8888));

    private static final String host = "http://127.0.0.1:8888";

    public WireMockSandbox objectUnderTest = new WireMockSandbox(new RestTemplate());

    @Test
    public void sendJsonPostRequestTest() throws IOException {
        //given
        final String path = "/acceptJson";
        RegisterRequestDTO requestBody = givenRegisterRequest();
        stubFor(post(urlEqualTo(path)).willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withBody("{\"status\": \"OK\"}")
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())));

        //when
        RegisterResponseDTO response = objectUnderTest.sendJsonPostRequest(host+path, requestBody);
        List<LoggedRequest> requests = findAll(RequestPatternBuilder.allRequests());
        LoggedRequest request = requests.get(0);

        //then
        Assert.assertEquals(RegisterResponseDTO.Status.OK, response.getStatus());
        Assert.assertEquals(JsonMapper.mapToString(requestBody), request.getBodyAsString());
    }

    private RegisterRequestDTO givenRegisterRequest(){
        RegisterRequestDTO requestDto = new RegisterRequestDTO();
        requestDto.setFirstName("firstName");
        requestDto.setLastName("lastName");
        requestDto.setEmail("test@test.fake");
        requestDto.setLogin("login");
        return requestDto;
    }
}
