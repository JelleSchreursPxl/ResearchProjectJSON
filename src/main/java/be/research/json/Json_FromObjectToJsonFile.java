package be.research.json;

import be.research.domain.Base;
import be.research.domain.Pokemon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static be.research.utils.PokemonListGenerator.GenerateListOfPokemons;

public class Json_FromObjectToJsonFile
{
    public static void main( String[] args )
    {
        // Generate Pokemon lists
         List<Pokemon> pokemons_10 = GenerateListOfPokemons(10);
         List<Pokemon> pokemons_100 = GenerateListOfPokemons(100);

        // From generated list to JSON file
         WriteJsonFile("src/assets/json/generated_10Pokemon_Json.json", pokemons_10);
         WriteJsonFile("src/assets/json/generated_100Pokemon_Json.json", pokemons_100);
    }

    private static void WriteJsonFile(String source, List<Pokemon> generatedList)
    {
        // lijst van objecten die we naar de file gaan wegschrijven
        JSONArray objectlist = new JSONArray();

        // we moeten elke pokemon ophalen om hier JSONObjecten en JSONArrays van te maken
        for (Pokemon pokemon:generatedList) {
            JSONObject object = new JSONObject();
            JSONArray type = new JSONArray();
            JSONArray base = new JSONArray();

            // plaats de id en name met key value in het object
            object.put("id", pokemon.getId());
            object.put("name", pokemon.getName());

            // loop door elke type en voeg elke type met key value toe aan het object
            Collections.addAll(type, pokemon.getTypes());
            object.put("type", type);

            // foreach door alle Base objecten van het Pokemon Object
            // maak eerst een JSONObject van elk Base Object en voeg dit JSONObject toe aan de JSONArray base
            for (Base pokemonbase:pokemon.getBase()) {
                JSONObject baseObject = new JSONObject();
                baseObject.put("name", pokemonbase.getName());
                baseObject.put("power", pokemonbase.getPower());
                base.add(baseObject);
            }

            // voeg de JSONArray base toe aan het JSONObject
            object.put("base", base);

            // voeg het object toe aan de JSONArray objectlist
            objectlist.add(object);
        }

        // Schrijf het JSONObject weg naar een nieuwe file
        try{
            FileWriter fileWriter = new FileWriter(source);
            fileWriter.write(objectlist.toJSONString());
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
