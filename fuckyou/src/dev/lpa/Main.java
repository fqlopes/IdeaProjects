package dev.lpa;


import dev.lpa.FUCKINGZOO.Animal;
import dev.lpa.FUCKINGZOO.Dog;

public class Main {
    public static void main(String[] args) {

        Animal dog = new Dog("Phyllis", "30");
        printShit(dog);

        System.out.println(dog.toString());

    }

    public static void printShit (Animal animal){

        animal.move();

    }
}