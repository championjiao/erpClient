package qw.openapi.sdk.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import qw.openapi.sdk.api.exception.JsonParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JacksonUtils() {
    }

    public static final ObjectMapper getInstance() {
        return objectMapper;
    }

    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            throw new JsonParseException();
        }
    }

    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException var3) {
            throw new JsonParseException();
        }
    }

    public static <T> T json2pojo(String jsonStr, JavaType javaType) {
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException var3) {
            throw new JsonParseException();
        }
    }

    public static <T> Map<String, Object> json2map(String jsonStr) {
        try {
            return (Map)objectMapper.readValue(jsonStr, Map.class);
        } catch (IOException var2) {
            throw new JsonParseException();
        }
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
        Map map;
        try {
            map = (Map)objectMapper.readValue(jsonStr, new TypeReference() {
            });
        } catch (IOException var6) {
            throw new JsonParseException();
        }

        HashMap result = new HashMap();
        Iterator var4 = map.entrySet().iterator();

        while(var4.hasNext()) {
            Entry entry = (Entry)var4.next();
            result.put(entry.getKey(), map2pojo((Map)entry.getValue(), clazz));
        }

        return result;
    }

    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        List list = null;

        try {
            list = (List)objectMapper.readValue(jsonArrayStr, new TypeReference() {
            });
        } catch (IOException var6) {
            throw new JsonParseException();
        }

        ArrayList result = new ArrayList();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            Map map = (Map)var4.next();
            result.add(map2pojo(map, clazz));
        }

        return result;
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    static {
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.getDeserializationConfig().withoutFeatures(new DeserializationFeature[]{DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES});
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
    }
}
