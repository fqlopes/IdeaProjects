package dev.lpa;

public class Truck implements Trackable{

    @Override
    public void track(){
        System.out.println(getClass().getSimpleName() + " is being tracked");
        double aux = FlightEnabled.KM_TO_MILES;

    }


}
