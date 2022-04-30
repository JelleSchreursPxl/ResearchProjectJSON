package be.research.domain;

import java.util.Arrays;
import java.util.List;

public class Pokemon {
    private int id;
    private String name;
    private String[] types;
    private List<Base> base;

    public Pokemon(){

    }

    public Pokemon(int id, String name, String[] types, List<Base> base) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.base = base;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getTypes() {
        return types;
    }

    public List<Base> getBase() {
        return base;
    }

    @Override
    public String toString() {
        return "Pokemon{ " +
                "Id=" + id +
                ", Name='" + name + '\'' +
                ", Types=" + Arrays.toString(types) +
                ", Base=" + base +
                " }";
    }

    public void saySomething() {
        System.out.println("Hi, I'm pokemon: " + name + " and I love cookies");
    }
}
