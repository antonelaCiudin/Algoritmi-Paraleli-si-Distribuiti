package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

public class SimpleStrictOne implements Intersection{
    private int nrIntersections;
    private int totalWaintingTime;
    public Semaphore[] semaphores;

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
            semaphores[i] = new Semaphore(1);
    }
}
