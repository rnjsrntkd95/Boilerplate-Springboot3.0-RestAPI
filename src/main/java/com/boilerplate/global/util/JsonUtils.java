package com.boilerplate.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.boot.json.JsonParseException;

@UtilityClass
public class JsonUtils {

    public String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonParseException();
        }
    }
}
