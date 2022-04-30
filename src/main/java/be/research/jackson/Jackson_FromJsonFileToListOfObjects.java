package be.research.jackson;

import be.research.domain.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Jackson_FromJsonFileToListOfObjects {
    public static void main(String[] args) {
        // From JSON file to Pokemon objects
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemon = Arrays.asList(objectMapper.readValue(Paths.get("src/main/resources/pokemon_10.json").toFile(), Pokemon[].class));
            pokemon.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println();


        try{
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemon = Arrays.asList(objectMapper.readValue(Paths.get("src/main/resources/pokemon_100.json").toFile(), Pokemon[].class));
            pokemon.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
