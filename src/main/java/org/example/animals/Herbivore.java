package org.example.animals;

import org.example.environment.Island;
import org.example.environment.Location;
import org.example.environment.Plant;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void eat(Location location) {
        if (satiety >= foodNeeded) return;
        List<Plant> plants = location.getPlants();
        int i = 0;
        while (i < plants.size() && satiety < foodNeeded) {
            Plant plant = plants.get(i);
            satiety = Math.min(satiety + plant.getWeight(), foodNeeded);
            plants.remove(i);
        }
    }

    @Override
    public void move(Island island, Location currentLocation) {
        if (speed == 0) return;
    }
    //реализовать движение

    @Override
    public void reproduce(Location location) {
        if (satiety < foodNeeded * 3) return;
        long sameSpeciesCount = location.getAnimals().stream()
                .filter(a -> a.getClass() == this.getClass())
                .filter(Animal::isAlive)
                .count();
        //реализовать размножение через фабрику животных
    }
}
