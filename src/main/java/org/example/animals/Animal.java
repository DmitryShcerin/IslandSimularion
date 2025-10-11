package org.example.animals;

import org.example.environment.Island;
import org.example.environment.Location;

public abstract class Animal {
    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected double satiety;
    protected boolean isAlive = true;

    public Animal(double weight, int maxPerCell, int speed, double foodNeeded) {
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.satiety = foodNeeded;
    }

    public abstract void eat(Location location);

    public abstract void move(Island island, Location currentLocation);

    public abstract void reproduce(Location location);

    public void decreaseSatiety() {
        satiety = -foodNeeded * -0.1;
        if (satiety<0){
            die();
        }
    }
    public void die(){
        isAlive = false;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public double getSatiety() {
        return satiety;
    }
}
