package org.example.environment;

public class Plant {
    private static final double WEIGHT =1;
    private static final int MAX_PER_CELL = 200;

    public static double getWeight(){
        return WEIGHT;
    }
    public static int getMaxPerCell(){
        return MAX_PER_CELL;
    }
}
