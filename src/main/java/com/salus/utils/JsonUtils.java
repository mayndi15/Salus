package com.salus.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
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

    public static <T> T deserialize(String jsonStr, Class<T> clazz) throws SalusException {

        T obj = null;

        try {
            obj = mapper.readValue(jsonStr, clazz);

            if (!isJson(obj.toString())) {
                throw new SalusException(SalusExceptionEnum.JSON_DESERIALIZE_INVALID);
            }

        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        return obj;
    }

    public static boolean isJson(String jsonStr) {

        if (jsonStr == null) {
            return false;
        }

        try {
            JsonParser json = mapper.createParser(jsonStr);

            if (json == null) {
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
