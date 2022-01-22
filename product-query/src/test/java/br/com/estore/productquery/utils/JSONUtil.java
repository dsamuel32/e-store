package br.com.estore.productquery.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T fromString(final String json, final Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Could not convert JSON to object.", e);
        }
        return null;
    }
    public static <T> T fromString(final String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<T>() {});
        } catch (JsonProcessingException e) {
            log.error("Could not convert JSON to object.", e);
        }
        return null;
    }

    public static <T> T fromString(final String json, final TypeReference<T> type) {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return MAPPER.readValue(json, type);
        } catch (JsonProcessingException e) {
            log.error("Could not convert JSON to object.", e);
        }
        return null;
    }

    public static <T> T fromFile(final String path, Class<T> clazz) {
        final String json = FileUtil.readFile(path);
        return fromString(json, clazz);
    }
    public static  <T> T fromFile(final String path, final TypeReference<T> type) {
        final String json = FileUtil.readFile(path);
        return fromString(json, type);
    }

    public static String toJson(final Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("Could not convert JSON to object.", e);
        }
        return null;
    }
}
