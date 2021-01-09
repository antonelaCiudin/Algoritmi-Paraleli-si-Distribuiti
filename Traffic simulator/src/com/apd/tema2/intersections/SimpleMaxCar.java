package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

public class SimpleMaxCar implements Intersection {
    private int nrIntersections;
    private int totalWaintingTime;
    private int maxCars;
    public Semaphore[] semaphores;

    public void setMaxCars(int maxCars) {
        this.maxCars = maxCars;
    }

    public int getMaxCars() {
        return maxCars;
    }

    public int getTotalWaintingTime() {
        return totalWaintingTime;
    }

    public void setTotalWaintingTime(int totalWaintingTime) {
        this.totalWaintingTime = totalWaintingTime;
    }

    public void setNrIntersections(int nrIntersections) {
        this.nrIntersections = nrIntersections;
    }

    public int getNrIntersections() {
        return nrIntersections;
    }

    public void initSemaphores() {
        this.semaphores = new Semaphore[this.nrIntersections];
        for (int i = 0; i < this.nrIntersections; i++)
            semaphores[i] = new Semaphore(this.maxCars);
    }
}
