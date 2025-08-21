public class Dog extends Animal {

    //Dog specific fields/variables
    private String earShape;
    private String tailShape;


    public Dog (){
        //  super(); --> This calls the Animal(no-args) constructor, and it must be declared first
        super ("Mammal", "All Sizes", 500);

    }

    public Dog (String type, double weight){
        this (type, weight, "Perky", "Curl"); //constructor chaining
    }

    public Dog(String type, double weight, String earShape, String tailShape) {
        super(type, weight < 15 ? "small": (weight < 35 ? "medium" : "large"), weight);
        this.earShape = earShape;
        this.tailShape = tailShape;
    }

    @Override
    public void move(String speed) {
        super.move(speed);
//        System.out.println("Dogs walk, run and wag their tail ");
        if (speed == "slow"){
            walk();
            wagTail();
        } else {
            run();
            bark();
        }
        System.out.println();

    }

    public void makeNoise(){
        if (type =="wolf"){
            System.out.print("A wolf howls and maybe ... ");
        }
        bark();
        System.out.println();

    }

    @Override
    public String toString() {
        return "Dog{" +
                "earShape='" + earShape + '\'' +
                ", tailShape='" + tailShape + '\'' +
                "} " + super.toString();
    }

    private void bark (){
        System.out.print (" Woof!");
    }

    private void run(){
        System.out.print (" The dog is running");
    }

    private void walk(){
        System.out.print(" The dog is walking");
    }

    private void wagTail(){
        System.out.print(" Tail wagging");
    }
}
