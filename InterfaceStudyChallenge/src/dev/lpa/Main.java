package dev.lpa;


public class Main {
    public static void main(String[] args) {

        var aux = new Building("Test", UsageType.GOVERNMENT);

        System.out.println(aux.toJSON());

    }
}
