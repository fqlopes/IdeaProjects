package dev.lpa.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public record Card (Suit suit, String face, int rank) {

    @Override
    public String toString(){
        return "%-8s - %-2s (RANK %-2d)%n".formatted( suit, face, rank);
    }

    public enum Suit {
        CLUBS,DIAMONDS, HEARTS, SPADES
    }

    public static List<Card> getNumericCard (Suit suit){
        List <Card> numericCard = new ArrayList<>();
        if (suit != null) {
            for (int i = 2; i <= 10; i++) {
                numericCard.add(new Card(suit, String.valueOf(i), i - 2)) ;
            }
        }
        return numericCard;
    }

    public static List<Card> getFaceCard (Suit suit){
        List <Card> faceCard = new ArrayList<>();
        if (suit != null) {
            String faceString = "JQKA";

            for (int i = 0; i < faceString.length() ; i++){
                faceCard.add(new Card(suit, String.valueOf(faceString.charAt(i)), i + 9));
            }
        }
        return faceCard;
    }

    public static List<Card> getDeck (){

        List<Card> newDeck = new ArrayList<>(52);
        var suits = Suit.values();

        for (Suit suit : suits){
            newDeck.addAll(getNumericCard(suit));
            newDeck.addAll(getFaceCard(suit));

        }


        return newDeck;
    }

    public static void printDeck (List<Card> deck, String description, int rows){

        System.out.println("-".repeat(32));
        if (description != null){
            System.out.println(description);
        }
        int cardInRow = deck.size()/rows;
        for (int i = 0 ; i < rows ; i++){
            int startIndex = i * cardInRow;
            int endIndex = startIndex + cardInRow;
            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }
}
