package org.example.animals.predators;

import org.example.animals.Animal;
import org.example.animals.Predator;

public class Eagle extends Predator {
    public Eagle(){
        super(6,20,3,1);
    }
    public double getChanceToEat(Class<? extends Animal> a){
        return switch (a.getSimpleName()){
            case "Rabbit", "Mouse" -> 90;
            case "Fox" -> 10;
            case "Duck" -> 80;
            default -> 0;
        };
    }
}