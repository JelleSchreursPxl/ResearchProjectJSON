package be.research.json;

import be.research.domain.Base;
import be.research.domain.Pokemon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Json_FromJsonFileToListOfObjects
{
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        // Json file omzetten naar een JSONArray om de key values te kunnen gebruiken
        JSONArray pokelist_10 = null;
        // JSONArray pokelist_100 = null;
        try {
            pokelist_10 = (JSONArray) parser.parse(new FileReader("src/main/resources/pokemon_10.json"));
            // pokelist_100 = (JSONArray) parser.parse(new FileReader("src/main/resources/pokemon_100.json"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        ReadJsonFileToPokemonObjects(pokelist_10);
        //ReadJsonFileToPokemonObjects(pokelist_100);
    }

    public static void ReadJsonFileToPokemonObjects(JSONArray pokelist)
    {
        List<Pokemon> pokemonList = new ArrayList<>();

        // We halen elke Object uit de lijst op om van hier PokemonObjecten van te maken
        for (Object poke : pokelist) {
            // we maken een JSONObject van van het object poke om de waarden met key value te kunnen ophalen
            JSONObject jsonobj = (JSONObject) poke;
            // We zorgen dat we een lijst hebben om onze Base objecten in bij te houden
            List<Base> baseList = new ArrayList<>();
            // we maken een JSONArray om de waarden van het JSONObject met key types in op te slaan waar we achteraf een String array van gaan maken
            JSONArray types = (JSONArray) jsonobj.get("types");
            String[] typesArray = new String[0];
            // we vullen een JSONArray met het JSONObject met de key value base
            JSONArray bases = (JSONArray) jsonobj.get("base");
            // we gaan een controle uitvoeren om te kijken of de JSONArray niet leeg is
            if (bases != null) {
                // we gaan van alle objecten in de JSONArray bases elk object appart ophalen om hier Base objecten van te maken
                for (Object baseObject : bases) {
                    JSONObject base = (JSONObject) baseObject;
                    // we vullen de baseList met de Base objecten van het object poke
                    baseList.add(new Base((String) base.get("name"), (int) (long) base.get("power")));
                }
            }
            // we kijken of de JSONArray niet leeg is
            if (types != null){
                typesArray = new String[types.size()];
                // we gaan de String[] typesArray opvullen met de individuele waarden van de JSONArray die worden omgezet naar een String
                for (int i = 0; i < types.size(); i++) {
                    typesArray[i] = types.get(i).toString();
                }
            }
            // we maken een Pokemonobject om dit dan toe te voegen aan een lijst van Pokemon
            pokemonList.add(new Pokemon((int) (long) jsonobj.get("id"), (String) jsonobj.get("name"), typesArray, baseList ));
        }

        for (Pokemon poke:pokemonList) {
            System.out.println(poke.getId());
            System.out.println(poke.getName());
            System.out.println(poke.getBase());
            System.out.println(Arrays.toString(poke.getTypes()));
        }
    }
}