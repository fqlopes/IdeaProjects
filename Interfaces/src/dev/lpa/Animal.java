package dev.lpa;

enum FlightStages implements Trackable {

    //Implementing an interface with an enum makes possible to traverse it's elements
    GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;

    @Override
    public void track() {

        if (this != GROUNDED){
            System.out.println("Monitoring " + this);
        }
    }

    public FlightStages getNextStage() {

        FlightStages[] allStages = values();
        //Returns the ordinal of this enumeration constant (its position in its enum declaration).
        return allStages[(ordinal() + 1) % allStages.length];
    }
}

record DragonFly (String name, String type) implements  FlightEnabled{

    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}

interface OrbitEarth extends FlightEnabled{




    public void achieveOrbit();

    private static void log (String description){

        var today = new java.util.Date();
        System.out.println(today + " description: " + description);

    }

    private void logStage(FlightStages stage, String description){

        description = stage + ": " + description;
        log(description);
    }

    @Override
    default FlightStages transition(FlightStages stage) {
        FlightStages nexStage = FlightEnabled.super.transition(stage);
        logStage(stage, "Beginning transition to " + nexStage );
        return nexStage;
    }
}

class Satellite implements OrbitEarth {

    FlightStages stage = FlightStages.GROUNDED;

    @Override
    public void achieveOrbit(){
        transition("Orbit achieved!");
    }

    @Override
    public void fly() {
        achieveOrbit();
        transition("Data collection while in orbit");

    }

    @Override
    public void takeOff() {
        transition("Taking Off");
    }

    @Override
    public void land() {
        transition("Landing");
    }

    public void transition(String description){
        System.out.println(description);
        stage = transition(stage);
        stage.track();
    }
}

interface FlightEnabled {

    //constants, these are public, static and final
    //they can be accessed anywhere by anyone
    public static final double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;

    void takeOff();
    void land();
    void fly();



    default FlightStages transition (FlightStages stage){
//        System.out.println("transition not implemented on " + this.getClass().getName());
//        return null;
        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from " + stage + "  to " + nextStage);
        return nextStage;
    }

}
interface Trackable {

    void track();

}

public abstract class Animal {

    public abstract void move();

}
