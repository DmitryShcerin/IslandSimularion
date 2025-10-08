package org.example.environment;


import org.example.animals.Animal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Plant> plants = new CopyOnWriteArrayList<>();
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 100; i++) {
            plants.add(new Plant());
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    public void addAnimal (Animal animal){
        if (animals.size()< getMaxAnimalsPerCell() ){
            animals.add(animal);
        }
    }
    private int getMaxAnimalsPerCell(){
        return 1000;
    }
}
