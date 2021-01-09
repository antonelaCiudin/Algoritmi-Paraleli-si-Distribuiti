package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Intersection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CyclicBarrier;

public class Railroad implements Intersection {
    public boolean trainState = false;

    public CyclicBarrier barrier = new CyclicBarrier(Main.carsNo);

    public Queue<Integer> idQueue = new LinkedList<>();
    public Queue<Integer> directionQueue = new LinkedList<>();

    public synchronized void addToQueue (int id, int direction) {
        idQueue.add(id);
        directionQueue.add(direction);
    }

    public synchronized void pollElements () {
        int[] result = new int[2];

        result[0] = idQueue.poll();
        result[1] = directionQueue.poll();

        System.out.println("Car " + result[0] + " from side number " + result[1] + " has started driving");
    }
}
