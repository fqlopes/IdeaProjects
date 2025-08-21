package dev.lpa.FUCKINGZOO;

public abstract class Animal {


    private String name;
    private String weight;
    private double age;

    public Animal (String name, String weight, double age){
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    public abstract void move ();


    @Override
    public String toString(){
        return "[%s] TYPE=> %s %.0f Y/O%n".formatted(name,getClass().getSimpleName(), age);
    }
}
