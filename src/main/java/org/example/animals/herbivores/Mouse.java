package org.example.animals.herbivores;

import org.example.animals.Animal;
import org.example.animals.Herbivore;
import org.example.environment.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivore {
    public Mouse(){
        super(1,500,1,1);
    }
    @Override
    public void eat(Location location){
        for (Animal a:location.getAnimals()){
            if (a instanceof Caterpillar && a != this && a.isAlive()){
                if (ThreadLocalRandom.current().nextDouble(100)<90){
                    satiety = Math.min(satiety+a.getWeight(),foodNeeded);
                    a.die();
                    return;
                }
            }
        }
    }
}
