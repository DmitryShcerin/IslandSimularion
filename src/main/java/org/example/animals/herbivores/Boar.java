package org.example.animals.herbivores;

import org.example.animals.Animal;
import org.example.animals.Herbivore;
import org.example.environment.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Boar extends Herbivore {
    public Boar() {
        super(400, 50, 2, 50);
    }

    @Override
    public void eat(Location location) {
        for (Animal a : location.getAnimals()) {
            if ((a instanceof Mouse || a instanceof Caterpillar) && a != this && a.isAlive()) {
                double chance = a instanceof Mouse ? 50 : 90;
                if (ThreadLocalRandom.current().nextDouble(100) < chance) {
                    satiety = Math.min(satiety + a.getWeight(), foodNeeded);
                    a.die();
                    return;
                }
            }
        }
    }
}
