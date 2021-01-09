package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

public class SimpleRoundabout implements Intersection {
    private int maxCars;
    private int totalWaintingTime;
    public Semaphore semaphore;

    public int getMaxCars() {
        return maxCars;
    }

    public int getTotalWaintingTime() {
        return totalWaintingTime;
    }

    public void setTotalWaintingTime(int totalWaintingTime) {
        this.totalWaintingTime = totalWaintingTime;
    }

    public void setMaxCars(int maxCars) {
        this.maxCars = maxCars;
    }
}
