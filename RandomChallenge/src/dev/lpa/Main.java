package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        List<Integer> diceList = new ArrayList<>();

        int dices =5;
        loadDices(diceList, dices);
        System.out.println("Your "+ dices + " dices are :" + diceList);

        System.out.println("Choose the position you want to change:");
        for (int i = 0; i < dices; i++){
            System.out.printf("Press %d to remove [%d]%n",(i+1), diceList.get(i) );
        }
        String input = scanner.nextLine();

        removeDice(diceList, input);

    }

    public static void loadDices (List<Integer> myList, int dices){
        if (dices != 0) {
                random.ints(dices, 1,7)
                        .forEach(myList::add);
        }
    }

    public static void removeDice (List<Integer> myList, String diceIndex){
       if (Integer.parseInt(diceIndex) > myList.size() || Integer.parseInt(diceIndex) == 0) {

           System.out.println("Something went wrong! ");
       } else {
           int realIndex = Integer.parseInt(diceIndex) - 1;
           myList.remove(realIndex);
           System.out.println("Removed, your list is now:");
           System.out.println(myList);
       }



    }
}
