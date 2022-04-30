package be.research.jackson;

import be.research.domain.Pokemon;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;

public class JacksonExample {
    public static void main(String[] args) {
        // Generate Pokemon lists
        List<Pokemon> pokemons_10 = GenerateListOfPokemons(10);
        List<Pokemon> pokemons_100 = GenerateListOfPokemons(100);

        // From generated list to JSON file
        WriteJsonFile("src/assets/jackson/generated_10Pokemons_Json.json", pokemons_10);
        WriteJsonFile("src/assets/jackson/generated_100Pokemons_Json.json", pokemons_100);


        // From JSON file to Pokemon objects
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemons = Arrays.asList(objectMapper.readValue(Paths.get("src/main/resources/pokemon_10.json").toFile(), Pokemon[].class));
            pokemons.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemons = Arrays.asList(objectMapper.readValue(Paths.get("src/main/resources/pokemon_100.json").toFile(), Pokemon[].class));
            pokemons.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private static void WriteJsonFile(String source, List<Pokemon> pokemons) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(source), pokemons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
