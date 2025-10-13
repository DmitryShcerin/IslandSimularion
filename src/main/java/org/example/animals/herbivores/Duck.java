package org.example.animals.herbivores;

import org.example.animals.Animal;
import org.example.animals.Herbivore;
import org.example.environment.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore {
    public Duck() {
        super(1, 200, 4, 1);
    }

    @Override
    public void eat(Location location) {
        for (Animal a : location.getAnimals()) {
            if (a instanceof Caterpillar && a != this && a.isAlive()) {
                if (ThreadLocalRandom.current().nextDouble(100) < 90) {
                    satiety = Math.min(satiety + a.getWeight(), foodNeeded);
                    a.die();
                    return;
                }
            }

        }
    }
}
