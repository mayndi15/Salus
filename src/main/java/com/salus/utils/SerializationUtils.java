package com.salus.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salus.rest.BaseJson;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SerializationUtils {

    private static ObjectMapper om;

    @Autowired(required = false)
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @PostConstruct
    public void init() {
        // Try to find Jackson object mapper on spring's message converter through spring context
        om = null;
        if (mappingJackson2HttpMessageConverter != null) {
            om = mappingJackson2HttpMessageConverter.getObjectMapper();
        }

        if (om == null) {
            // Default, local object mapper
            om = new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    public static BaseJson deserializeJson(String jsonStr) {
        return fromJson(jsonStr, BaseJson.class);
    }

    private static <T> T fromJson(String jsonStr, Class<T> clazz) {
        if (StringUtils.isBlank(jsonStr) || clazz == null) {
            return null;
        }

        T obj = null;
        try {
            obj = om.readValue(jsonStr, clazz);
        } catch (IOException ex) {
            Logger.getLogger(SerializationUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
}
