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
    private static final String POKEMON_JSON_FILE_PATH_10 = "src/assets/jsonio/generated_10Pokemons_Json.json";
    private static final String POKEMON_JSON_FILE_PATH_100 = "src/assets/jsonio/generated_100Pokemons_Json.json";
    private static final String POKEMON_JSON_FILE_PATH_10_WO_TYPE = "src/assets/jsonio/generated_10Pokemons_without_type.json";
    private static final String POKEMON_JSON_FILE_PATH_100_WO_TYPE = "src/assets/jsonio/generated_100Pokemons_without_type.json";

    public static void main(String[] args) throws Exception {
        JsonIoObjectToJson();
        JsonIoObjectToJsonWithoutTypes();
        JsonIoJsonToObject();
    }

    private static void JsonIoObjectToJson() {
        // From object to json
        String jsonStr_10 = objectToJson(POKEMON_10);
        String jsonStr_100 = objectToJson(POKEMON_100);

        // Pretty-print JSON
        String s_10 = JsonWriter.formatJson(jsonStr_10);
        String s_100 = JsonWriter.formatJson(jsonStr_100);

        JsonIoWriter(s_10, POKEMON_JSON_FILE_PATH_10);
        JsonIoWriter(s_100, POKEMON_JSON_FILE_PATH_100);
    }

    private static void JsonIoObjectToJsonWithoutTypes() {
        // From object to json without types
        Map typeArgs = new HashMap();
        typeArgs.put(JsonWriter.TYPE, false);
        String jsonStrWoTypes_10 = objectToJson(POKEMON_10, typeArgs);
        String jsonStrWoTypes_100 = objectToJson(POKEMON_100, typeArgs);

        // Pretty-print JSON without types
        String s_10 = JsonWriter.formatJson(jsonStrWoTypes_10);
        String s_100 = JsonWriter.formatJson(jsonStrWoTypes_100);

        JsonIoWriter(s_10, POKEMON_JSON_FILE_PATH_10_WO_TYPE);
        JsonIoWriter(s_100, POKEMON_JSON_FILE_PATH_100_WO_TYPE);
    }

    private static void JsonIoJsonToObject() throws Exception {
        String json_10 = readFileAsString(POKEMON_JSON_FILE_PATH_10);
        String json_100 = readFileAsString(POKEMON_JSON_FILE_PATH_100);
        String json_10_wo_types = readFileAsString(POKEMON_JSON_FILE_PATH_10_WO_TYPE);
        String json_100_wo_types = readFileAsString(POKEMON_JSON_FILE_PATH_100_WO_TYPE);

        // From json to object
        Object pkmJsonStrToObject_10 = jsonToJava(json_10);
        Object pkmJsonStrToObject_100 =  jsonToJava(json_100);
        Object pkmJsonStrToObject_10_wo_types =  jsonToJava(json_10_wo_types);
        Object pkmJsonStrToObject_100_wo_types =  jsonToJava(json_100_wo_types);

        System.out.println(pkmJsonStrToObject_10);
        System.out.println(pkmJsonStrToObject_100);
        System.out.println(pkmJsonStrToObject_10_wo_types);
        System.out.println(pkmJsonStrToObject_100_wo_types);
    }

    private static void JsonIoWriter(String s, String path) {
        try (BufferedWriter objToJsonWriter = new BufferedWriter(new FileWriter(path))) {
            objToJsonWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
