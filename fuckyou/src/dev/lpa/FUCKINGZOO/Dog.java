package dev.lpa.FUCKINGZOO;

public class Dog extends Animal {

    private double age;
    @Override
    public void move() {
        System.out.printf(
                """
                Move, bitch, get out the way
                Get out the way, bitch, get out the way
                Move, bitch, get out the way
                Get out the way, bitch, get out the way
                %s %s%n
                """.toUpperCase(),"NOW","GO");
    }


    public Dog (String name, String weight){
        super(name, weight, 0);
        this.age = name.compareTo(weight);
    }



}
