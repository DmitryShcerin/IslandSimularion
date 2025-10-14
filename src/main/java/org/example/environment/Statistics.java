package org.example.environment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    private final Map<String, AtomicInteger> animalCounts = new ConcurrentHashMap<>();
    private final Map<String, AtomicInteger> deathCauses = new ConcurrentHashMap<>();
    private final AtomicInteger plantCount = new AtomicInteger();
    private final AtomicInteger plantsGrown = new AtomicInteger();

    public void reset() {
        animalCounts.clear();
        deathCauses.clear();
        plantsGrown.set(0);
        plantCount.set(0);
    }

    public void recordAnimal(String type) {
        animalCounts.computeIfAbsent(type, k -> new AtomicInteger()).incrementAndGet();
    }

    public void recordDeath(String type, String cause) {
        deathCauses.computeIfAbsent(type + "_" + cause, k -> new AtomicInteger()).incrementAndGet();
    }

    public void recordPlants(int count) {
        plantCount.addAndGet(count);
    }
     public void recordPlantGrowth(){
        plantsGrown.incrementAndGet();
    }

    public void printStatistics(int cycle) {
        System.out.println("\n=== Цикл " + cycle + " ===");
        System.out.println("Смерти:");
        deathCauses.forEach((k, v) -> System.out.println("  " + k + ": " + v));
        System.out.println("Животные:");
        animalCounts.forEach((k, v) -> System.out.println("  " + k + ": " + v));
        System.out.println("Растения: " + plantCount.get());
        System.out.println("Новые растения: " + plantsGrown.get());
    }
}
