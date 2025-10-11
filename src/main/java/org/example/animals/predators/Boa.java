package org.example.animals.predators;

import org.example.animals.Animal;
import org.example.animals.Predator;

public class Boa extends Predator {
    public Boa(){
        super(15,30,1,3);
    }
    public double getChanceToEat(Class<? extends Animal> a){
        return switch (a.getSimpleName()){
            case "Fox" -> 15;
            case "Rabbit" -> 20;
            case "Mouse" -> 40;
            case "Duck" -> 10;
            default -> 0;
        };
    }
}
