package be.research.json;

import be.research.domain.Base;
import be.research.domain.Pokemon;

import javax.json.*;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;

public class Json_FromObjectToJsonFile {

    private static final List<Pokemon> POKEMON_10 = GenerateListOfPokemons(10);
    private static final List<Pokemon> POKEMON_100 = GenerateListOfPokemons(100);
    private static final List<Pokemon> POKEMON_1000 = GenerateListOfPokemons(1000);
    private static final List<Pokemon> POKEMON_10000 = GenerateListOfPokemons(10000);

    private static final String WRITE_WITH_10_POKEMON = "src/assets/json/generated_10Pokemon_Json.json";
    private static final String WRITE_WITH_100_POKEMON = "src/assets/json/generated_100Pokemon_Json.json";
    private static final String WRITE_WITH_1000_POKEMON = "src/assets/json/generated_1000Pokemon_Json.json";
    private static final String WRITE_WITH_10000_POKEMON = "src/assets/json/generated_10000Pokemon_Json.json";

    public static void main(String[] args) {

        CreateJsonListFromPokemonObjects(POKEMON_10, WRITE_WITH_10_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_100, READ_WITH_100_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_1000, READ_WITH_1000_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_10000, READ_WITH_10000_POKEMON);

        CreateJsonListFromPokemonObjects(POKEMON_10, WRITE_WITH_10_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_100, WRITE_WITH_100_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_1000, WRITE_WITH_1000_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_10000, WRITE_WITH_10000_POKEMON);
    }

    static void CreateJsonListFromPokemonObjects(List<Pokemon> pokemonList, String writeToLocation) {
        JsonArrayBuilder listBuilder = Json.createArrayBuilder();
        for (Pokemon pokemon : pokemonList) {

            JsonArrayBuilder typeBuilder = Json.createArrayBuilder();
            for (String type:pokemon.getTypes()) {
                typeBuilder.add(type);
            }

            JsonArrayBuilder baseBuilder = Json.createArrayBuilder();
            for (Base base : pokemon.getBase()) {
                baseBuilder.add(Json.createObjectBuilder()
                        .add("name", base.getName())
                        .add("power", base.getPower()).build());
            }

            //System.out.println(Arrays.stream(types).toList());
            JsonObject pokemonObject = Json.createObjectBuilder()
                    .add("id", pokemon.getId())
                    .add("name", pokemon.getName())
                    .add("types", typeBuilder)
                    .add("base", baseBuilder).build();


//            JsonObjectBuilder pokemonBuilder = Json.createObjectBuilder();
//            pokemonBuilder.add("pokemon", pokemonObject);
            listBuilder.add(pokemonObject);
        }
        JsonArray arr = listBuilder.build();


        // Schrijf het JSONObject weg naar een nieuwe file
        try {
            FileWriter fileWriter = new FileWriter(writeToLocation);
            fileWriter.write(String.valueOf(arr));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

