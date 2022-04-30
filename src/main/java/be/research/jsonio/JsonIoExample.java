package be.research.jsonio;

import be.research.domain.Pokemon;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;
import static com.cedarsoftware.util.io.JsonReader.jsonToJava;
import static com.cedarsoftware.util.io.JsonWriter.objectToJson;

public class JsonIoExample {
    public static void main(String[] args) throws IOException {
        BufferedWriter objToJsonWriter = new BufferedWriter(new FileWriter("src/assets/jsonio/generated_objectojson.json"));
        BufferedWriter strToObjectWriterWoType = new BufferedWriter(new FileWriter("src/assets/jsonio/generated_objectojsonwotype_Json.json"));
        int count = 0;

        List<Pokemon> pokemons_100 = GenerateListOfPokemons(100);
        for (Pokemon pokemon: pokemons_100) {
            // From object to json
            String jsonStr = objectToJson(pokemon);
            // Pretty-print JSON
            String s = JsonWriter.formatJson(jsonStr);

            // From json to object
            Pokemon pkmJsonStrToObject = (Pokemon) jsonToJava(jsonStr);
            System.out.println(pkmJsonStrToObject);

            // From object to json without types
            Map typeArgs = new HashMap();
            typeArgs.put(JsonWriter.TYPE, false);
            String jsonStrWoTypes = objectToJson(pokemon, typeArgs);
            // Pretty-print JSON without types
            String s2 = JsonWriter.formatJson(jsonStrWoTypes);

            // No built-in method in json-io to write pretty json to file?? (like in Jackson library)
            // Custom implementation/workaround to write pretty json to file with json-io
//            JsonWriter.writeJsonUtf8String(jsonStr, writer);
            count++;
            writeCustomPrettyJson(objToJsonWriter, count, pokemons_100, s);
            writeCustomPrettyJson(strToObjectWriterWoType, count, pokemons_100, s2);
        }
    }

    private static void writeCustomPrettyJson(BufferedWriter objToJsonWriter, int count, List<Pokemon> pokemon, String s) throws IOException {
        // Place bracket at first index to declare an array
        if(count == 1){
            objToJsonWriter.write("[");
        }

        // Write the prettified json string
        objToJsonWriter.write(s);

        // End the array on the last index
        if(count == pokemon.size()){
            objToJsonWriter.write("]");
        // Don't place a comma on the end of the json file, only inbetween json objects
        }else{
            objToJsonWriter.write(  ",");
        }
    }
}
