package be.research.jackson;

import be.research.domain.Pokemon;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.util.List;

import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;

public class JacksonFromObjectListToJsonFile {
    public static void main(String[] args) {
        /** First step of the Jackson parser
         *  Generate a list of objects ( In this case Pokemon )
         *   -> Based on the domain class Pokemon
         **/
        List<Pokemon> pokemons_10 = GenerateListOfPokemons(10);
        // List<Pokemon> pokemons_100 = GenerateListOfPokemons(100);
        //List<Pokemon> pokemons_1000 = GenerateListOfPokemons(1000);
        //List<Pokemon> pokemons_10000 = GenerateListOfPokemons(10000);

        /** Generating a JSON list based on the given list of objects **/
        WriteJsonFile("src/assets/jackson/generated_10Pokemon_Json.json", pokemons_10);
        //WriteJsonFile("src/assets/jackson/generated_100Pokemon_Json.json", pokemons_100);
        //WriteJsonFile("src/assets/jackson/generated_1000Pokemon_Json.json", pokemons_1000);
        //WriteJsonFile("src/assets/jackson/generated_10000Pokemon_Json.json", pokemons_10000);
    }

    /**
     * The WriteJsonFile method has two parameters as described above
     * Writing a file is expected in a Try-Catch-block
     * @param source -> a String is expected for the location
     * @param pokemons -> list of objects that are being parsed
     * PrettyPrinter gives an pretty outlined print for each object
     */

    private static void WriteJsonFile(String source, List<Pokemon> pokemons) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(source), pokemons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
