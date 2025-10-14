package org.example.simulation;


import org.example.environment.Island;

public class IslandSimulation {
    private final Island island;
    private int cycle = 0;

    public IslandSimulation(int width, int height){
        this.island = new Island(width,height);
    }

    public void start(int totalCycles){
        for (int i = 0; i< totalCycles;i++){
            runSimulationCycle();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    private void runSimulationCycle(){
        System.out.println("\n=== Запуск цикла симуляции ===");
        cycle++;
        island.runCycle();
        island.getStatistics().printStatistics(cycle);
    }

    public static void main(String[] args) {
        IslandSimulation simulation = new IslandSimulation(20, 20);
        simulation.start(10);
    }
}