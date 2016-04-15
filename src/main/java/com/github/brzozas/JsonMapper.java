package com.github.brzozas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonMapper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String mapToString(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(obj.getClass() + "is not valid JSON object");
        }
    }

    public static <T> T mapToObject(String jsonString, Class<T> clazz){
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("["+jsonString+"] is not valid JSON");
        }
    }
}
