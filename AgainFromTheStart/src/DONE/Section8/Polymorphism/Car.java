package DONE.Section8.Polymorphism;

public class Car {

    private boolean engine;
    private int cylinders;
    private String name;
    private int wheels;

    public Car (int cylinders, String name){
        this.cylinders = cylinders;
        this.name = name;
        this.wheels =4;
        this.engine = true;
    }


    public String startEngine(){
        return this.getClass().getSimpleName() + " -> startEngine()";
    }

    public String accelerate(){
        return this.getClass().getSimpleName() +" -> accelerate()";
    }

    public String brake(){
        return this.getClass().getSimpleName() + " -> brake()";
    }

    public String getName(){
        return this.name;
    }

    public int getCylinders(){
        return this.cylinders;
    }


}
