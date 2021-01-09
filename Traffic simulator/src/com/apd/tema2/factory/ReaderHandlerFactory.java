package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Intersection;
import com.apd.tema2.entities.IntersectionHandler;
import com.apd.tema2.entities.Pedestrians;
import com.apd.tema2.entities.ReaderHandler;
import com.apd.tema2.intersections.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 * Returneaza sub forma unor clase anonime implementari pentru metoda de citire din fisier.
 */
public class ReaderHandlerFactory {

    public static ReaderHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of them)
        // road in maintenance - 1 lane 2 ways, X cars at a time
        // road in maintenance - N lanes 2 ways, X cars at a time
        // railroad blockage for T seconds for all the cars
        // unmarked intersection
        // cars racing
        ReaderHandler result = null;
        switch (handlerType) {
            case "simple_semaphore" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) {
                    // Exemplu de utilizare:
                    Main.intersection = IntersectionFactory.getIntersection("simpleIntersection");
                }
            };
                break;
            case "simple_n_roundabout" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    // To parse input line use:
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("simpleRoundabout");
                    SimpleRoundabout simpleRoundabout = (SimpleRoundabout) Main.intersection;
                    simpleRoundabout.setMaxCars(Integer.parseInt(line[0]));
                    simpleRoundabout.setTotalWaintingTime(Integer.parseInt(line[1]));
                    simpleRoundabout.semaphore = new Semaphore(simpleRoundabout.getMaxCars());
                }
            };
                break;
            case "simple_strict_1_car_roundabout" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("simpleStrict1CarRoundabout");
                    SimpleStrictOne simpleStrictOne = (SimpleStrictOne) Main.intersection;
                    simpleStrictOne.setNrIntersections(Integer.parseInt(line[0]));
                    simpleStrictOne.setTotalWaintingTime(Integer.parseInt(line[1]));
                    simpleStrictOne.initSemaphores();
                }
            };
                break;
            case "simple_strict_x_car_roundabout" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("simpleStrictXCarRoundabout");
                    SimpleStrictX simpleStrictX = (SimpleStrictX) Main.intersection;
                    simpleStrictX.setNrIntersections(Integer.parseInt(line[0]));
                    simpleStrictX.setTotalWaintingTime(Integer.parseInt(line[1]));
                    simpleStrictX.setMaxCars(Integer.parseInt(line[2]));
                    simpleStrictX.initSemaphores();
                }
            };
                break;
            case "simple_max_x_car_roundabout" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("simpleMaxXCarRoundabout");
                    SimpleMaxCar simpleMaxCar = (SimpleMaxCar) Main.intersection;
                    simpleMaxCar.setNrIntersections(Integer.parseInt(line[0]));
                    simpleMaxCar.setTotalWaintingTime(Integer.parseInt(line[1]));
                    simpleMaxCar.setMaxCars(Integer.parseInt(line[2]));
                    simpleMaxCar.initSemaphores();
                }
            };
                break;
            case "priority_intersection" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("priorityIntersection");
                }
            };
                break;
            case "crosswalk" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("crosswalk");
                    Crosswalk crosswalk = (Crosswalk) Main.intersection;
                    crosswalk.setTotalWaintingTime(Integer.parseInt(line[0]));
                    crosswalk.setMaxPedestrians(Integer.parseInt(line[1]));
                    crosswalk.init();
                }
            };
                break;
            case "simple_maintenance" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("simpleMaintenance");
                }
            };
                break;
            case "complex_maintenance" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("complexMaintenance");
                }
            };
                break;
            case "railroad" : result = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = IntersectionFactory.getIntersection("railroad");
                }
            };
                break;
            default : result = null;
        };
        return result;
    }

}
