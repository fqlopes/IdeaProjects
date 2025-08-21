package dev.lpa;

abstract class Mammal extends Animal {


    public Mammal(String type, String size, int weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {

        System.out.print(getExplicitType() + " ");
        System.out.println(speed.equals("slow") ? "walks" : "runs");
    }

    public abstract void shedHair();
}

public abstract class Animal {

    protected String type;
    private String size;
    private int weight;

    public Animal(String type, String size, int weight) {

        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public final String getExplicitType () {
        return getClass().getSimpleName() +  " (" + type  + ")";
    }

    public abstract void move(String speed);

    public abstract void makeNoise();
}
