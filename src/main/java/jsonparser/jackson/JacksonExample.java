package jsonparser.jackson;

import com.fasterxml.jackson.databind.*;
import jsonparser.jackson.objects.Pokemon;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JacksonExample {
    public static void main(String[] args) {
        // Generate Pokemon lists
        List<Pokemon> pokemons_10 = GenerateListOfPokemons(10);
        List<Pokemon> pokemons_100 = GenerateListOfPokemons(100);

        // From generated list to JSON file
        WriteJsonFile("src/assets/generated_10Pokemons_Json.json", pokemons_10);
        WriteJsonFile("src/assets/generated_100Pokemons_Json.json", pokemons_100);


        // From JSON file to Pokemon objects -- oplossen!
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemons = Arrays.asList(objectMapper.readValue(Paths.get("src/main/pokemon_10.json").toFile(), Pokemon[].class));
            pokemons.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pokemon> pokemons = Arrays.asList(objectMapper.readValue(Paths.get("src/main/pokemon_100.json").toFile(), Pokemon[].class));
            pokemons.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    // methods
    private static @NotNull List<Pokemon> GenerateListOfPokemons(int length) {
        return PokemonListGenerator.generatePokemonlist(length);
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
