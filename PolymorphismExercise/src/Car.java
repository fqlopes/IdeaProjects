public class Car {

    private boolean engine;
    private int cylinders;
    private String name;
    private int wheels;

    public Car(boolean engine, int cylinders, String name, int wheels) {
        this.engine = engine;
        this.cylinders = cylinders;
        this.name = name;
        this.wheels = wheels;
    }

    public Car(int cylinders, String name) {
        this(true, cylinders, name, 4);
        this.cylinders = cylinders;
        this.name = name;
    }

    public String startEngine() {
        return getClass().getSimpleName() + " -> startEngine()";
    }

    public String accelerate() {
        return getClass().getSimpleName() + " -> accelerate()";
    }

    public String brake() {
        return getClass().getSimpleName() + " -> brake()";
    }



//    @Override
//    public String toString() {
//        return "Car{" +
//                "engine=" + engine +
//                ", cylinders=" + cylinders +
//                ", name='" + name + '\'' +
//                ", wheels=" + wheels +
//                '}';
//    }
}

class Mitsubishi extends Car {

    public Mitsubishi(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return super.startEngine();
    }

    @Override
    public String accelerate() {
        return super.accelerate();
    }

    @Override
    public String brake() {
        return super.brake();
    }
}

class Holden extends Car {

    public Holden(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return super.startEngine();
    }

    @Override
    public String accelerate() {
        return super.accelerate();
    }

    @Override
    public String brake() {
        return super.brake();
    }
}

class Ford extends Car {

    public Ford(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return super.startEngine();
    }

    @Override
    public String accelerate() {
        return super.accelerate();
    }

    @Override
    public String brake() {
        return super.brake();
    }
}


