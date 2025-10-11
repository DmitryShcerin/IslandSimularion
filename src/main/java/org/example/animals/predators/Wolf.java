package org.example.animals.predators;

import org.example.animals.Animal;
import org.example.animals.Predator;

public class Wolf extends Predator {
    public Wolf(){
        super(50,30,3,8);
    }
    public double getChanceToEat(Class<? extends Animal> a){
        return switch (a.getSimpleName()){
            case "Rabbit", "Goat" -> 60;
            case "Mouse" -> 80;
            case "Sheep" -> 70;
            case "Boar", "Deer" -> 15;
            case "Duck" -> 40;
            case "Buffalo" -> 10;
            default -> 0;
        };
    }
}
