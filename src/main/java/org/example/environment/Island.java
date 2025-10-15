package org.example.environment;

import org.example.animals.Animal;
import org.example.animals.AnimalFactory;
import org.example.animals.herbivores.*;
import org.example.animals.predators.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    private final Location[][] locations;
    private final int width, height;
    private final Statistics statistics;

    private static final Map<Class<? extends Animal>, AnimalFactory> factories = new HashMap<>();

    static {
        factories.put(Wolf.class, Wolf::new);
        factories.put(Boa.class, Boa::new);
        factories.put(Fox.class, Fox::new);
        factories.put(Bear.class, Bear::new);
        factories.put(Eagle.class, Eagle::new);
        factories.put(Horse.class, Horse::new);
        factories.put(Deer.class, Deer::new);
        factories.put(Rabbit.class, Rabbit::new);
        factories.put(Mouse.class, Mouse::new);
        factories.put(Goat.class, Goat::new);
        factories.put(Sheep.class, Sheep::new);
        factories.put(Boar.class, Boar::new);
        factories.put(Buffalo.class, Buffalo::new);
        factories.put(Duck.class, Duck::new);
        factories.put(Caterpillar.class, Caterpillar::new);
    }

    public static AnimalFactory getAnimalFactory(Class<? extends Animal> type) {
        return factories.get(type);
    }

    public Island(int width, int height) {
        this.locations = new Location[width][height];
        this.height = height;
        this.width = width;
        this.statistics = new Statistics();
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                locations[x][y] = new Location(x, y);
        initializeAnimals();
    }

    public void initializeAnimals() {
        Random random = new Random();
        for (Location[] row : locations) {
            for (Location location : row) {
                for (AnimalFactory factory : factories.values()) {
                    int count = random.nextInt(3) + 1;
                    for (int i = 0; i < count; i++) {
                        location.addAnimal(factory.create());
                    }
                }
            }
        }
    }

    public Location getLocation(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height) ? locations[x][y] : null;
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

    public void runCycle() {
        // Сброс статистики
        for (Location[] row : locations)
            for (Location loc : row)
                for (Animal animal : new ArrayList<>(loc.getAnimals()))
                    if (animal.isAlive()) animal.eat(loc);
        for (Location[] row : locations)
            for (Location loc : row)
                for (Animal animal : new ArrayList<>(loc.getAnimals()))
                    if (animal.isAlive()) animal.reproduce(loc);
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations)
            for (Location loc : row)
                allAnimals.addAll(loc.getAnimals());
        Collections.shuffle(allAnimals);

        for (Animal animal : allAnimals)
            if (animal.isAlive())
                for (Location[] row : locations)
                    for (Location loc : row)
                        if (loc.getAnimals().contains(animal)) {
                            animal.move(this, loc);
                            break;
                        }
        for (Location[] row : locations) {
            for (Location loc : row) {
                List<Animal> animals = loc.getAnimals();
                for (Animal animal:animals){
                    animal.decreaseSatiety();
                }
                animals.removeIf(animal -> {
                    if (!animal.isAlive()) {
                        statistics.recordDeath(animal.getClass().getSimpleName(), "hunger");
                        return true;
                    }
                    return false;
                });

                int toGrow = ThreadLocalRandom.current().nextInt(5,15);
                for (int i = 0; i<toGrow;i++){
                    if (loc.getPlants().size()<Plant.getMaxPerCell()){
                        loc.getPlants().add(new Plant());
                        statistics.recordPlantGrowth();
                    }
                }
            }
        }
        for (Location[] row : locations)
            for (Location loc:row){
                for (Animal a:loc.getAnimals())
                    statistics.recordAnimal(a.getClass().getSimpleName());
                statistics.recordPlants(loc.getPlants().size());
            }
    }

}
