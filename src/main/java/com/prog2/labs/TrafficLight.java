package com.prog2.labs;

import java.util.concurrent.Semaphore;

class TrafficLight {

    private Semaphore greenLightRoadA;
    private Semaphore greenLightRoadB;

    public TrafficLight() {
        greenLightRoadA = new Semaphore(1);
        greenLightRoadB = new Semaphore(0);
    }

    public void carArrived(
            int carId, // ID of the car
            int roadId, // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction, // Direction of the car
            Runnable turnGreen, // Use turnGreen.run() to turn light to green on the current road
            Runnable crossCar // Use crossCar.run() to make the car cross the intersection
    ) {
        if (roadId == 1) {
            try {
                greenLightRoadA.acquire(); 
                turnGreen.run(); //Turn the traffic light to green on road A
                crossCar.run(); //Let the car to cross the intersection
                greenLightRoadB.release(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                greenLightRoadB.acquire(); 
                turnGreen.run(); //Turn the traffic light to green on road B
                crossCar.run(); //Allow the car to cross the intersection
                greenLightRoadA.release(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
