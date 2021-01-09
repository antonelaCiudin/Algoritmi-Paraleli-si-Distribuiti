package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.*;
import com.apd.tema2.intersections.*;
import com.apd.tema2.utils.Constants;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

/**
 * Clasa Factory ce returneaza implementari ale InterfaceHandler sub forma unor
 * clase anonime.
 */
public class IntersectionHandlerFactory {

    public static IntersectionHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of
        // them)
        // road in maintenance - 2 ways 1 lane each, X cars at a time
        // road in maintenance - 1 way, M out of N lanes are blocked, X cars at a time
        // railroad blockage for s seconds for all the cars
        // unmarked intersection
        // cars racing
        IntersectionHandler result = null;
        switch (handlerType) {
            case "simple_semaphore" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    System.out.println("Car " + car.getId() + " has reached the semaphore, now waiting...");
                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car " + car.getId() + " has waited enough, now driving...");
                }
            };
                break;
            case "simple_n_roundabout" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    SimpleRoundabout simpleRoundabout = (SimpleRoundabout) Main.intersection;
                    System.out.println("Car " + car.getId() + " has reached the roundabout, now waiting...");
                    try {
                        simpleRoundabout.semaphore.acquire();
                        System.out.println("Car " + car.getId() + " has entered the roundabout");
                        try {
                            sleep(simpleRoundabout.getTotalWaintingTime());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car " + car.getId() + " has exited the roundabout after " +
                            simpleRoundabout.getTotalWaintingTime() / 1000 + " seconds");
                    simpleRoundabout.semaphore.release();
                }
            };
                break;
            case "simple_strict_1_car_roundabout" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    SimpleStrictOne simpleStrictOne = (SimpleStrictOne) Main.intersection;
                    System.out.println("Car " + car.getId() + " has reached the roundabout");
                    for (int i = 0; i < simpleStrictOne.getNrIntersections(); i++) {
                        if (car.getStartDirection() == i) {
                            try {
                                simpleStrictOne.semaphores[i].acquire();
                                System.out.println("Car " + car.getId() + " has entered the roundabout from lane " + i);
                                try {
                                    sleep(simpleStrictOne.getTotalWaintingTime());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Car " + car.getId() + " has exited the roundabout after " +
                                        simpleStrictOne.getTotalWaintingTime() / 1000 + " seconds");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            simpleStrictOne.semaphores[i].release();
                        }
                    }
                }
            };
                break;
            case "simple_strict_x_car_roundabout" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                }
            };
                break;
            case "simple_max_x_car_roundabout" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    // Get your Intersection instance
                    SimpleMaxCar simpleMaxCar = (SimpleMaxCar) Main.intersection;

                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

                    System.out.println("Car " + car.getId() + " has reached the roundabout from lane " + car.getStartDirection());
                    // Continuati de aici
                    for (int i = 0; i < simpleMaxCar.getNrIntersections(); i++) {
                        if (car.getStartDirection() == i) {
                            try {
                                simpleMaxCar.semaphores[i].acquire();
                                System.out.println("Car " + car.getId() + " has entered the roundabout from lane " + i);
                                try {
                                    sleep(simpleMaxCar.getTotalWaintingTime());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Car " + car.getId() + " has exited the roundabout after " +
                                        simpleMaxCar.getTotalWaintingTime() / 1000 + " seconds");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            simpleMaxCar.semaphores[i].release();
                        }
                    }
                }
            };
                break;
            case "priority_intersection" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    // Get your Intersection instance

                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

                    // Continuati de aici
                }
            };
                break;
            case "crosswalk" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    Crosswalk crosswalk = (Crosswalk) Main.intersection;

                    while(!Main.pedestrians.isFinished()){
                        if (!Main.pedestrians.isPass()) {
                            if (crosswalk.messages.get(car.getId()) == null || crosswalk.messages.get(car.getId()).equals("red")) {
                                System.out.println("Car " + car.getId() + " has now green light");
                                crosswalk.messages.set(car.getId(), "green");
                            }
                        }
                        else if (crosswalk.messages.get(car.getId()) == null || crosswalk.messages.get(car.getId()).equals("green")) {
                            System.out.println("Car " + car.getId() + " has now red light");
                            crosswalk.messages.set(car.getId(), "red");
                        }
                    }
                    if (crosswalk.messages.get(car.getId()) == null || crosswalk.messages.get(car.getId()).equals("red")) {
                        System.out.println("Car " + car.getId() + " has now green light");
                        crosswalk.messages.set(car.getId(), "green");
                    }
                }
            };
                break;
            case "simple_maintenance" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                }
            };
                break;
            case "complex_maintenance" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                }
            };
                break;
            case "railroad" : result = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    Railroad railroad = (Railroad) Main.intersection;
                    System.out.println("Car " + car.getId() + " from side number " + car.getStartDirection() + " has stopped by the railroad");
                    railroad.addToQueue(car.getId(), car.getStartDirection());
                    try {
                        railroad.barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    if (car.getId() == 0) {
                        System.out.println("The train has passed, cars can now proceed");
                        railroad.trainState = true;
                    }
                    try {
                        railroad.barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    railroad.pollElements();
                }
            };
                break;
            default : result = null;
        };

        return result;
    }
}
