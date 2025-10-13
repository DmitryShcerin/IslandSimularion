package org.example.animals.herbivores;

import org.example.animals.Herbivore;
import org.example.environment.Island;
import org.example.environment.Location;

public class Caterpillar extends Herbivore {
    public Caterpillar(){
        super(1,1000,0,0);
    }
    @Override
    public void move(Island island,Location loc){}
    @Override
    public void decreaseSatiety(){}
}
