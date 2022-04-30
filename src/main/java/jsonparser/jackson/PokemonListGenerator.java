package jsonparser.jackson;

import jsonparser.jackson.objects.Pokemon;
import jsonparser.jackson.objects.Base;
import jsonparser.jackson.objects.PokemonNames;
import jsonparser.jackson.objects.Types;

import java.util.*;

public class PokemonListGenerator {
    public static List<Pokemon> generatePokemonlist(int listLength) {
        List<Pokemon> pokemonList = new ArrayList<>();
        for (int i = 1; i <= listLength; i++) {
            String pokemonName = randomName();
            String[] types = (i % 3 == 0)
                    ? new String[]{randomType(), randomType()}
                    : new String[]{randomType()};
            List<Base> pokemonStats = generateBase();

            Pokemon pokemon = new Pokemon(i, pokemonName, types, pokemonStats);
            pokemonList.add(pokemon);
        }
        return pokemonList;
    }

    public static List<Base> generateBase() {
        Random random = new Random();

        List<Base> pokemonStats = new ArrayList<>();
        Base HPstats = new Base("HP", random.nextInt(200));
        Base Attackstats = new Base("Attack", random.nextInt(200));
        Base Defensestats = new Base("Defense", random.nextInt(200));

        pokemonStats.add(HPstats);
        pokemonStats.add(Attackstats);
        pokemonStats.add(Defensestats);

        return pokemonStats;
    }

    private static String randomName(){
        return PokemonNames.values()[new Random().nextInt(PokemonNames.values().length)].toString().toLowerCase();
    }

    private static String randomType(){
        return Types.values()[new Random().nextInt(Types.values().length)].toString().toLowerCase();
    }
}
