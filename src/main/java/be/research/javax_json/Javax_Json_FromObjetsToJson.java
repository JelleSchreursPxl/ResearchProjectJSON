package be.research.javax_json;

import be.research.domain.Pokemon;
import com.google.gson.JsonElement;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Javax_Json_FromObjetsToJson {
    public static void main(String[] args) throws IOException {
        InputStream fileInputStream = new FileInputStream("src/main/resources/pokemon_10.json");
        JsonReader jsonReader = Json.createReader(fileInputStream);
        JsonArray jsonObjects = jsonReader.readArray();

        jsonReader.close();
        fileInputStream.close();

        System.out.println(jsonObjects.get(0));

        // logica TOM
        for(JsonValue pokemon : jsonObjects){
            JsonObject po = pokemon.asJsonObject();
            Pokemon pokemon1 = new Pokemon();
            pokemon1.setId(Integer.parseInt(po.get("id").toString()));
            pokemon1.setName(String.valueOf(po.get("name")));
            String id = po.get("id").toString();
            System.out.println(pokemon1.getId());
        }
    }
}
