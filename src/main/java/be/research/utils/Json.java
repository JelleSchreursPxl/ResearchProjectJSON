package be.research.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper =  new ObjectMapper();
        defaultObjectMapper.registerModule(new JavaTimeModule());
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static JsonNode parse(String source) throws IOException {
        return objectMapper.readTree(source);
    }

    public static <A> A fromJson(JsonNode node, Class<A> aClass) throws JsonProcessingException {
        return objectMapper.treeToValue(node, aClass);
    }

    public static JsonNode toJson(Object object){
        return objectMapper.valueToTree(object);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return getoutlinedPrint(node, false);
    }

    public static String outlinedPrint(JsonNode node) throws JsonProcessingException {
        return getoutlinedPrint(node, true);
    }

    private static String getoutlinedPrint(JsonNode node, boolean outlined) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if(outlined) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(node);
    }
}


