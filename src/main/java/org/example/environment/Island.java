package org.example.environment;

public class Island {
    private final Location[][] locations;
    private final int width, height;
    private final Statistics statistics;

    public Island(int width, int height){
        this.locations = new Location[width][height];
        this.height = height;
        this.width = width;
        this.statistics = new Statistics();
    }

    public Location[][] getLocations() {
        return locations;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
