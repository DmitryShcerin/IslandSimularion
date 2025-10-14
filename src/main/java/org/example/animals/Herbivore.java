package org.example.animals;

import org.example.environment.Island;
import org.example.environment.Location;
import org.example.environment.Plant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void move(Island island, Location currentLocation) {
        if (speed == 0) return;

        int newX = currentLocation.getX();
        int newY = currentLocation.getY();

        int direction = ThreadLocalRandom.current().nextInt(4);
        int steps = ThreadLocalRandom.current().nextInt(1, speed + 1);

        switch (direction) {
            case 0 -> newX += steps;
            case 1 -> newX -= steps;
            case 2 -> newY += steps;
            case 3 -> newY -= steps;
        }

        newX = Math.max(0, Math.min(newX, island.getWidth() - 1));
        newY = Math.max(0, Math.min(newY, island.getHeight() - 1));

        Location newLocation = island.getLocation(newX, newY);
        if (newLocation != currentLocation) {
            currentLocation.getAnimals().remove(this);
            newLocation.addAnimal(this);
        }
    }


    @Override
    public void reproduce(Location location) {
        if (satiety < foodNeeded * 3) return;
        long sameSpeciesCount = location.getAnimals().stream()
                .filter(a -> a.getClass() == this.getClass())
                .filter(Animal::isAlive)
                .count();
        if (sameSpeciesCount>=2&&sameSpeciesCount<maxPerCell){
            AnimalFactory factory = Island.getAnimalFactory(this.getClass());
            if (factory!=null){
                Animal offspring = factory.create();
                location.addAnimal(offspring);
            }
        }
    }
}
