package be.research.javax_json;

import be.research.domain.Base;
import be.research.domain.Pokemon;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static be.research.javax_json.Json_FromObjectToJsonFile.CreateJsonListFromPokemonObjects;
import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;

public class Json_FromJsonFileToListOfObjects {

    private static final List<Pokemon> POKEMON_10 = GenerateListOfPokemons(10);
    private static final List<Pokemon> POKEMON_100 = GenerateListOfPokemons(100);
    private static final List<Pokemon> POKEMON_1000 = GenerateListOfPokemons(1000);
    private static final List<Pokemon> POKEMON_10000 = GenerateListOfPokemons(10000);

    private static final String READ_WITH_10_POKEMON = "src/assets/javax_json/generated_10Pokemon_Json.json";
    private static final String READ_WITH_100_POKEMON = "src/assets/javax_json/generated_100Pokemon_Json.json";
    private static final String READ_WITH_1000_POKEMON = "src/assets/javax_json/generated_1000Pokemon_Json.json";
    private static final String READ_WITH_10000_POKEMON = "src/assets/javax_json/generated_10000Pokemon_Json.json";

    public static void main(String[] args) throws IOException {

        CreateJsonListFromPokemonObjects(POKEMON_10, READ_WITH_10_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_100, READ_WITH_100_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_1000, READ_WITH_1000_POKEMON);
        //CreateJsonListFromPokemonObjects(POKEMON_10000, READ_WITH_10000_POKEMON);


        ReadJsonFileToPokemonObjects(READ_WITH_10_POKEMON);
        //WritePokemonObjectsToJsonFile(READ_WITH_100_POKEMON);
        //WritePokemonObjectsToJsonFile(READ_WITH_1000_POKEMON);
        //WritePokemonObjectsToJsonFile(READ_WITH_10000_POKEMON);
    }

    static void ReadJsonFileToPokemonObjects(String readPokemonList) throws IOException {
        InputStream fileInputStream = new FileInputStream(readPokemonList);
        JsonReader jsonReader = Json.createReader(fileInputStream);
        JsonArray jsonObjects = jsonReader.readArray();
        jsonReader.close();
        fileInputStream.close();

        List<Pokemon> pokemonList = new ArrayList<>();

        for (JsonValue object : jsonObjects) {
            JsonObject jobj = object.asJsonObject();
            List<Base> baseList = new ArrayList<>();
            String[] types = new String[jobj.get("types").asJsonArray().size()];

            for (JsonValue b : jobj.get("base").asJsonArray()) {
                Base base = new Base(
                        removeQuotes(b.asJsonObject().get("name").toString()),
                        Integer.parseInt(b.asJsonObject().get("power").toString()));
                baseList.add(base);
            }

            JsonArray typesArray = jobj.get("types").asJsonArray();
            for (int i = 0; i < typesArray.size(); i++) {
                types[i] = removeQuotes(typesArray.get(i).toString());
            }
            Pokemon pokemon = new Pokemon(
                    Integer.parseInt(jobj.get("id").toString()),
                    removeQuotes(jobj.get("name").toString()), types, baseList);
            pokemonList.add(pokemon);
        }
    }

    private static String removeQuotes(String input) {
        return input.substring(1, input.length() - 1);
    }
}
