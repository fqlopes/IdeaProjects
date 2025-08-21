package dev.lpa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l","Hel","o");
        String dText = "He".concat("llo");
        String eText = "hello";

        List<String> hellos = Arrays.asList(aText, bText,cText,dText,eText);

        hellos.forEach(s -> System.out.println(s + ": " + s.hashCode()));

        Set<String> mySet = new HashSet<>(hellos);

        System.out.println("mySet = " + mySet);
        System.out.println("# of elements = " + mySet.size());

        for (var setValue : mySet){
            System.out.print(setValue + ": ");
            for (int i = 0; i < hellos.size(); i++){
                if (setValue == hellos.get(i)) {
                    System.out.print(i + ", ");
                }
            }
        }
        System.out.println(" ");


        PlayingCard aceHearts = new PlayingCard("Hearts", "Ace");
        PlayingCard kingClubs = new PlayingCard("Clubs", "King");
        PlayingCard queenSpades = new PlayingCard("Spades", "Queen");

        List<PlayingCard> cards = Arrays.asList(aceHearts, kingClubs, queenSpades);

        cards.forEach(s-> System.out.println(s + ": " + s.hashCode()));

        //Creating a set of cards

        Set<PlayingCard> deck = new HashSet<>();
        //loop through the cards List, adding each element one at the time
        for (var card : cards){
            //the .add method is boolean, returns true if a value was added, false if not
            if(!deck.add(card)){
                System.out.println("Found duplicate of  " + card);
            }
        }
        System.out.println(deck);
    }
}
