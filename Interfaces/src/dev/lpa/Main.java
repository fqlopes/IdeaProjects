package dev.lpa;

//JDK8 and above ->interface can use default and public static methods
//JDK9 and above ->interface now supports private methods
//These methods are always concrete

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Bird bird = new Bird();
        Animal animal = bird;
        Trackable tracked = bird; // Trackable trackable = new Bird();
        FlightEnabled flier = bird;

        bird.move();
        animal.move();
//        tracked.move();
//        flyer.move();

//        flier.takeOff();
//        flier.fly();
//        tracked.track();
//        flier.land();

        inFlight(flier);

        inFlight(new Jet()); // passing a new instance off Jet without assign a variable to it. Can't be accessed

        Trackable truck = new Truck(); // we may only access the Trackable interface methods
        truck.track();

        double kmsTraveled = 100;
        double milesTraveled = kmsTraveled * FlightEnabled.KM_TO_MILES;
        System.out.printf("The truck traveled %.2f km or %.2f miles %n", kmsTraveled, milesTraveled);


        LinkedList<FlightEnabled> fliers = new LinkedList<>();
        fliers.add(bird);

        List<FlightEnabled> betterFliers = new LinkedList<>();
        betterFliers.add(bird);

        triggerFliers(fliers);
        flyFliers(fliers);
        landFliers(fliers);

        triggerFliers(betterFliers);
        flyFliers(betterFliers);
        landFliers(betterFliers);

    }

    private static void inFlight (FlightEnabled flier){

        flier.takeOff();
        flier.fly();
        if (flier instanceof Trackable tracked){
            tracked.track();
        }
        flier.land();
    }

    private static void triggerFliers(List<FlightEnabled> fliers){

        for (var flier : fliers){
            flier.takeOff();
        }
    }

    private static void flyFliers(List<FlightEnabled> fliers){

        for (var flier : fliers){
            flier.fly();
        }
    }

    private static void landFliers(List<FlightEnabled> fliers){

        for (var flier : fliers){
            flier.land();
        }
    }
}
