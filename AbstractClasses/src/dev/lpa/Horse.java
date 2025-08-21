package dev.lpa;

public class Horse extends Mammal{


    public Horse(String type, String size, int weight) {
        super(type, size, weight);
    }

    @Override
    public void shedHair() {

        System.out.println(getExplicitType() + " sheds hair in the spring");

    }

    @Override
    public void makeNoise() {

    }
}
