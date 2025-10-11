package org.example.animals;

import org.example.environment.Island;
import org.example.environment.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void eat(Location location) {
        if (satiety >= foodNeeded) return;

        List<Animal> potentialPrey = new ArrayList<>(location.getAnimals());
        Collections.shuffle(potentialPrey);

        for (Animal prey : potentialPrey) {
            if (prey == this || !prey.isAlive()) continue;
            double chance = getChanceToEat(prey.getClass());
            if (ThreadLocalRandom.current().nextDouble(100) < chance) {
                satiety = Math.min(satiety + prey.getWeight(), foodNeeded);
                prey.die();
                break;
            }
        }
    }

    public abstract double getChanceToEat(Class<? extends Animal> animalType);

    @Override
    public void move(Island island, Location currentLocation) {
        if (speed == 0) return;
    }
    // Реализовать движение

    @Override
    public void reproduce(Location location) {
        if (satiety < foodNeeded * 3) return;
        long sameSpeciesCount = location.getAnimals().stream()
                .filter(a -> a.getClass() == this.getClass())
                .filter(Animal::isAlive)
                .count();
        // реализовать размножение через фабрику животных
    }
}
