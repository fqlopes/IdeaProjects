package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class Card {

    //quero rotacionar entre os indices do naipe
    private static final List<String> naipes = new ArrayList<>(List.of("COPAS", "OUROS", "PAUS", "ESPADAS"));

    //rotacionando lista
    private String naipe;

    //de 2 a 10, e J Q K A
    private String valor;

    //vai servir de índice
    private int rank;

    public Card (String naipe, String valor, int rank){
        this.naipe = naipe;
        this.valor = valor;
        this.rank = rank;
    }


    @Override
    public String toString(){


        return "%s-%s-(%d)".formatted(valor, naipe, rank);

    }
    public static Card getNumberedCard (String naipe, int valor){

        if (valor > 1 && valor < 11){
            return new Card (naipe, String.valueOf(valor), valor -2);
        }
        System.out.println("Deu merda em alguma coisa");
        return null;
    }

    public static Card getFacedCard (String naipe, char letra){

        int index = "JQKA".indexOf(letra);

        if (index > -1){
            return new Card (naipe, String.valueOf(letra), index + 9 );
        }
        return null;

    }

    public static List<Card>gerarDeck () {

        List<Card>deckComum = new ArrayList<>(52);
        naipes.forEach(naipe -> {
                           for (int i = 2; i <= 10; i++){
                    deckComum.add(getNumberedCard(naipe,i));
                }
                for (char c : new char[]{'J','Q','K','A' }){
                    deckComum.add(getFacedCard(naipe, c));
                }


        });
        return deckComum;
    }

    public static void printDeck (List<Card> deck){
        printDeck(deck,"Default Deck", 4);
    }

    public static void printDeck (List<Card> deck, String description, int rows){
        if (description != null){
            System.out.println(description);
        }
        int cardsInRow = deck.size()/rows; // 52 cards in 4 rows => 13 cards per row

        //using a for loop to place the cards, using the previous created method
        for (int i = 0; i < rows; i++){
            int startIndex = i * cardsInRow; // first loop == 0 * 13 = 0
            int endIndex = startIndex + cardsInRow; // first loop == 0 + 13 = 13;


            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " ")); //lambda loop
            System.out.println();


        }
    }


}
