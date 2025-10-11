package org.example.animals.predators;

import org.example.animals.Animal;
import org.example.animals.Predator;

public class Fox extends Predator {
    public Fox(){
        super(8,30,2,2);
    }
    public double getChanceToEat(Class<? extends Animal> a){
        return switch (a.getSimpleName()){
            case "Rabbit" -> 70;
            case "Mouse" -> 90;
            case "Caterpillar" -> 40;
            case "Duck" -> 60;
            default -> 0;
        };
    }
}