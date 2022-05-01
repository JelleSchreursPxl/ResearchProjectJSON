package be.research.gson;

import be.research.domain.Pokemon;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Gson_FromJsonFileToListOfObjects {
    /**
     * Main program that has 2 actions
     * 1) Creating 2 Strings which represents a location where the file can be found;
     * 2) Creating 2 Lists filled with Pokemons using a Deserialization method
     */
    public static void main(String[] args) {
        String jsonFile10Pokemons ="src/assets/gson/generated_10Pokemon_Json.json";
        String jsonFile100Pokemons ="src/assets/gson/generated_100Pokemon_Json.json";

        List<Pokemon> pokemons_10 = GsonDeserialization(jsonFile10Pokemons);
        List<Pokemon> pokemons_100 = GsonDeserialization(jsonFile100Pokemons);
    }


    /**
     * Creating a Gson-Object to read a JSON file to a list of Pokemon-Objects
     * @param location a string that represents a path where the file can be found
     * @return a list filled with Pokemon Objects converted from a JSON file
     */
    private static List<Pokemon> GsonDeserialization(String location) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        List<Pokemon> pokemons = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(location))) {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return gson.fromJson(sb.toString(), new TypeToken<List<Pokemon>>(){}.getType());
    }
}
