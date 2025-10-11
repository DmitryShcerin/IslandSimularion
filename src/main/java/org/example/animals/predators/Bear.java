package org.example.animals.predators;

import org.example.animals.Animal;
import org.example.animals.Predator;

public class Bear extends Predator {
    public Bear() {
        super(500, 5, 2, 80);
    }

    public double getChanceToEat(Class<? extends Animal> a) {
        return switch (a.getSimpleName()) {
            case "Boa", "Deer", "Rabbit" -> 80;
            case "Horse" -> 40;
            case "Mouse" -> 90;
            case "Goat", "Sheep" -> 70;
            case "Boar" -> 50;
            case "Buffalo" -> 20;
            case "Duck" -> 10;
            default -> 0;
        };
    }
}
