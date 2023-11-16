package com.prog2.labs;

/**
 * @author adinashby
 *
 */
public class LabTwenty {

    /**
     * Write your test code below in the main (optional).
     *
     */
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        simulateCarArrival(1, 1, 2, trafficLight);
        simulateCarArrival(2, 1, 1, trafficLight);
        simulateCarArrival(3, 1, 2, trafficLight);
        simulateCarArrival(4, 2, 4, trafficLight);
        simulateCarArrival(5, 2, 3, trafficLight);
    }

    private static void simulateCarArrival(int carId, int roadId, int direction, TrafficLight trafficLight) {
        Thread carThread = new Thread(new Runnable() {
            public void run() {
                trafficLight.carArrived(carId, roadId, direction, new Runnable() {
                    public void run() {
                        System.out.println("Traffic Light on Road " + roadId + " Is Green");
                    }
                }, new Runnable() {
                    public void run() {
                        System.out.println("Car " + carId + " Has Passed Road " + ((roadId == 1) ? "A" : "B")
                                + " In Direction " + direction);
                    }
                });
            }
        });
        carThread.start();
    }
}
