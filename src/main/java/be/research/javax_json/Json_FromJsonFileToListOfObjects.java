package be.research.javax_json;

import be.research.domain.Base;
import be.research.domain.Pokemon;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Json_FromJsonFileToListOfObjects {

    private static final String READ_WITH_10_POKEMON = "src/assets/javax_json/generated_10Pokemon_Json.json";
    private static final String READ_WITH_100_POKEMON = "src/assets/javax_json/generated_100Pokemon_Json.json";
    private static final String READ_WITH_1000_POKEMON = "src/assets/javax_json/generated_1000Pokemon_Json.json";
    private static final String READ_WITH_10000_POKEMON = "src/assets/javax_json/generated_10000Pokemon_Json.json";

    public static void main(String[] args) throws IOException {

        WritePokemonObjectsToJsonFile(READ_WITH_10_POKEMON);
        WritePokemonObjectsToJsonFile(READ_WITH_100_POKEMON);
        WritePokemonObjectsToJsonFile(READ_WITH_1000_POKEMON);
        WritePokemonObjectsToJsonFile(READ_WITH_10000_POKEMON);
    }

    static void WritePokemonObjectsToJsonFile(String readPokemonList) throws IOException
    {
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
                Base base = new Base(removeQuotes(b.asJsonObject().get("name").toString()), Integer.parseInt(b.asJsonObject().get("power").toString()));
                baseList.add(base);
            }

            JsonArray typesArray = jobj.get("types").asJsonArray();
            for (int i = 0; i < typesArray.size(); i++) {
                    types[i] = removeQuotes(typesArray.get(i).toString());
                }
            Pokemon pokemon = new Pokemon(Integer.parseInt(jobj.get("id").toString()), removeQuotes(jobj.get("name").toString()), types, baseList);
            pokemonList.add(pokemon);
        }

        //System.out.println(pokemonList);
        pokemonList.get(1).saySomething();
        System.out.println(pokemonList.get(1).getClass().getName());
        System.out.println(pokemonList.get(1).getId());
        System.out.println(pokemonList.get(1).getName());
        System.out.println(Arrays.stream(pokemonList.get(1).getTypes()).toList().toString());
        System.out.println(pokemonList.get(1).getBase());
    }

        private static String removeQuotes (String input){
            return input.substring(1, input.length() - 1);
    }
}
