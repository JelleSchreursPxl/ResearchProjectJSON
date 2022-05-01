package be.research.gson;

import be.research.domain.Pokemon;
import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;

public class Gson_FromObjectListToJsonFile {
    /**
     * Main program that has 3 actions:
     * 1) Generating 2 Lists filled with Pokemons
     * 2) Creating 2 Strings which represents a location where the file can be written
     * 3) Serialization process with a Serialization method
     */
    public static void main(String[] args) {
        List<Pokemon> pokemons_10 = GenerateListOfPokemons(10);
        List<Pokemon> pokemons_100 = GenerateListOfPokemons(100);

        String gson10Pokemons = "src/assets/gson/generated_10Pokemon_Json.json";
        String gson100Pokemons = "src/assets/gson/generated_100Pokemon_Json.json";

        GsonWriteJsonFile(gson10Pokemons, pokemons_10);
        GsonWriteJsonFile(gson100Pokemons, pokemons_100);
    }

    /**
     * Creating Gson-Object to write Objects to a JSON file
     * FileWriter makes use of a Try-Catch-block with resources to close the writer when the job is finished
     * @param location a path where to write the file
     * @param pokemons a list with Pokemon Objects
     */
    private static void GsonWriteJsonFile(String location, List<Pokemon> pokemons){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try (FileWriter writer = new FileWriter(location)){
            writer.write(gson.toJson(pokemons));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
