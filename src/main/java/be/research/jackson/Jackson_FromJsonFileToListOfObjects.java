package be.research.jackson;

import be.research.domain.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Jackson_FromJsonFileToListOfObjects {
    public static void main(String[] args) {
        /**
         * Every try-catch block contains a deserialisation
         * This makes use of the ObjectMapper class
         * Giving it a Path to get as a file and read it in as a value to the ObjectMapper
         * This is then compared to the expected class and given back as an array but changed to a list
         */
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemon = Arrays.asList(objectMapper.readValue(Paths.get("src/main/resources/pokemon_10.json").toFile(), Pokemon[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemon = Arrays.asList(objectMapper.readValue(Paths.get("src/main/resources/pokemon_100.json").toFile(), Pokemon[].class));
            // pokemon.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemon = Arrays.asList(objectMapper.readValue(Paths.get("src/assets/jackson/generated_1000Pokemon_Json.json").toFile(), Pokemon[].class));
            // pokemon.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemon = Arrays.asList(objectMapper.readValue(Paths.get("src/assets/jackson/generated_10000Pokemon_Json.json").toFile(), Pokemon[].class));
            // pokemon.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
