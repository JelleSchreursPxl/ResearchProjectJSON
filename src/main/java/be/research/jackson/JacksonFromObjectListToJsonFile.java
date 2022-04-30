package be.research.jackson;

import be.research.domain.Pokemon;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.util.List;

import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;

public class JacksonFromObjectListToJsonFile {
    public static void main(String[] args) {
        // Generate Pokemon lists
        List<Pokemon> pokemons_10 = GenerateListOfPokemons(10);
        List<Pokemon> pokemons_100 = GenerateListOfPokemons(100);

        // From generated list to JSON file
        WriteJsonFile("src/assets/jackson/generated_10Pokemon_Json.json", pokemons_10);
        WriteJsonFile("src/assets/jackson/generated_100Pokemon_Json.json", pokemons_100);
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
