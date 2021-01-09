package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Intersection;
import com.apd.tema2.entities.Pedestrians;

import java.util.ArrayList;

public class Crosswalk implements Intersection {
    private int totalWaintingTime;
    private int maxPedestrians;

    public ArrayList<String> messages;
    public void setMaxPedestrians(int maxPedestrians) {
        this.maxPedestrians = maxPedestrians;
    }

    public void setTotalWaintingTime(int totalWaintingTime) {
        this.totalWaintingTime = totalWaintingTime;
    }

    public int getMaxPedestrians() {
        return maxPedestrians;
    }

    public int getTotalWaintingTime() {
        return totalWaintingTime;
    }

    public void init() {
        Main.pedestrians = new Pedestrians(this.totalWaintingTime, this.maxPedestrians);
        messages = new ArrayList<>();
        for (int i = 0; i < Main.carsNo; i++)
            messages.add(null);
    }
}
