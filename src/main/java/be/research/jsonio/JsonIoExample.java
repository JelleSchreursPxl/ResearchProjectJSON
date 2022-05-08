package be.research.jsonio;

import be.research.domain.Pokemon;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;
import static com.cedarsoftware.util.io.JsonReader.jsonToJava;
import static com.cedarsoftware.util.io.JsonWriter.objectToJson;

public class JsonIoExample {
    private static final List<Pokemon> POKEMON_10 = GenerateListOfPokemons(10);
    private static final List<Pokemon> POKEMON_100 = GenerateListOfPokemons(100);
    private static final List<Pokemon> POKEMON_1000 = GenerateListOfPokemons(1000);
    private static final List<Pokemon> POKEMON_10000 = GenerateListOfPokemons(10000);

    private static final String POKEMON_JSON_FILE_PATH_10 = "src/assets/jsonio/generated_10Pokemons_Json.json";
    private static final String POKEMON_JSON_FILE_PATH_100 = "src/assets/jsonio/generated_100Pokemons_Json.json";
    private static final String POKEMON_JSON_FILE_PATH_1000 = "src/assets/jsonio/generated_1000Pokemons_Json.json";
    private static final String POKEMON_JSON_FILE_PATH_10000 = "src/assets/jsonio/generated_10000Pokemons_Json.json";

    private static final String POKEMON_JSON_FILE_PATH_10_WO_TYPE = "src/assets/jsonio/generated_10Pokemons_without_type.json";
    private static final String POKEMON_JSON_FILE_PATH_100_WO_TYPE = "src/assets/jsonio/generated_100Pokemons_without_type.json";
    private static final String POKEMON_JSON_FILE_PATH_1000_WO_TYPE = "src/assets/jsonio/generated_1000Pokemons_without_type.json";
    private static final String POKEMON_JSON_FILE_PATH_10000_WO_TYPE = "src/assets/jsonio/generated_10000Pokemons_without_type.json";

    public static void main(String[] args) throws Exception {
        JsonIoObjectToJson(POKEMON_10, POKEMON_JSON_FILE_PATH_10);
        // JsonIoObjectToJsonWithoutTypes(POKEMON_10000, POKEMON_JSON_FILE_PATH_10000_WO_TYPE);
        // JsonIoJsonToObject(POKEMON_JSON_FILE_PATH_10000);
        // JsonIoJsonWithoutTypesToObject(POKEMON_JSON_FILE_PATH_10000_WO_TYPE);
    }

    private static void JsonIoObjectToJson(List<Pokemon> pokemon, String path) {
        // From object to json
        String jsonStr = objectToJson(pokemon);

        // Pretty-print JSON
        String s = JsonWriter.formatJson(jsonStr);

        JsonIoWriter(s, path);
    }

    private static void JsonIoObjectToJsonWithoutTypes(List<Pokemon> pokemon, String path) {
        // From object to json without types
        Map typeArgs = new HashMap();
        typeArgs.put(JsonWriter.TYPE, false);
        String jsonStrWoTypes = objectToJson(pokemon, typeArgs);

        // Pretty-print JSON without types
        String s = JsonWriter.formatJson(jsonStrWoTypes);

        JsonIoWriter(s, path);
    }

    private static void JsonIoJsonToObject(String path) throws Exception {
        String json = readFileAsString(path);
        // From json to object
        Object pkmJsonStrToObject = jsonToJava(json);

        System.out.println(pkmJsonStrToObject);
    }

    private static void JsonIoJsonWithoutTypesToObject(String path) throws Exception {
        String json_wo_types = readFileAsString(path);

        // From json without types to object
        Object pkmJsonStrToObject_wo_types = jsonToJava(json_wo_types);

        for (Object pkmJsonStrToObject_wo_type : ((Object[]) pkmJsonStrToObject_wo_types)) {
            System.out.println(pkmJsonStrToObject_wo_type.toString());
        }
    }

    private static void JsonIoWriter(String s, String path) {
        try (BufferedWriter objToJsonWriter = new BufferedWriter(new FileWriter(path))) {
            objToJsonWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
