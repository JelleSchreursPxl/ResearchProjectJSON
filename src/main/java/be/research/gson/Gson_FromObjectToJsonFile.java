package be.research.gson;

import be.research.domain.Base;
import be.research.domain.Pokemon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Gson_FromObjectToJsonFile {
    public static void main(String[] args) {
        String[] types = new String[2];
        types[0] = "Electric";
        types[1] = "Normal";
        List<Base> bases = new ArrayList<>();
        bases.add(new Base("Attack", 100));
        bases.add(new Base("Defense", 100));
        bases.add(new Base("HP", 9999));
        Pokemon pikachu = new Pokemon(25, "Pikachu", types, bases);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try (FileWriter writer = new FileWriter("src/assets/gson/generated_1Pokemon_Json.json")){
            writer.write(gson.toJson(pikachu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Path.of("src/assets/gson/generated_1Pokemon_Json.json"))) {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Pokemon pikachu2 = gson.fromJson(sb.toString(), Pokemon.class);
        pikachu2.saySomething();

    }
}
