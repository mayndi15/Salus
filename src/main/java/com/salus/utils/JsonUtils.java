package com.salus.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salus.exception.SalusException;
import com.salus.exception.SalusExceptionEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {

    private static ObjectMapper mapper;

    @Autowired(required = false)
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @PostConstruct
    public void init() {
        mapper = null;
        if (mappingJackson2HttpMessageConverter != null) {
            mapper = mappingJackson2HttpMessageConverter.getObjectMapper();
        }

        if (mapper == null) {
            mapper = new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    public static <T> T deserialize(String jsonStr, Class<T> clazz) throws SalusException, JsonProcessingException {

        T obj = mapper.readValue(jsonStr, clazz);

        if (!isJson(obj.toString())) {
            throw new SalusException(SalusExceptionEnum.JSON_DESERIALIZE_INVALID);
        }
        return obj;
    }

    public static boolean isJson(String json) {

        if (json == null) {
            return false;
        }

        try {
            mapper.createParser(json);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
