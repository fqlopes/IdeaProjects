package dev.lpa.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardGame   {

    private final List<Card> deck = Card.getDeck();

    private int playerCount;

    private int cardsInHand;

    private List<CardHand> cardGameHands;


    public CardGame (int players, int cardsInHand){
        cardGameHands = new ArrayList<>();
        this.playerCount = players;
        this.cardsInHand = cardsInHand;
    }


    public void playGame (){

        System.out.println("Starting a new game");
        System.out.println("-".repeat(32));
        System.out.println("Shuffling the deck");
        System.out.println("-".repeat(32));
        Collections.shuffle(deck);
        System.out.println("-".repeat(32));
        System.out.println("Cutting in the deck in half");

        Collections.rotate(deck, deck.size()/2);
        Card.printDeck(deck, "Ready to game", 4);

        dealAll();
        cardGameHands.forEach(System.out::println);

    }

//    public void deal(){
//        System.out.println("Dando as cartas, 1 carta para cada jogador por vez");
//        Card[][] hands = new Card[playerCount][cardsInHand];
//        for (int i = 0, cardIndex = 0; i< playerCount; i++){
//            for(int j = 0; j < cardsInHand ; j++){
//                //dando 1 carta para cada jogador por vez
//                hands[j][i] = deck.get(cardIndex++);
//            }
//        }
//
//        System.out.println("Players Hands");
//        for ( int i = 0; i < playerCount; i++){
//
//            System.out.println("Player " + i);
//            for (int j = 0; j < cardsInHand; j++){
//
//                System.out.println(hands[i][j]);
//            }
//
//        }
//
//    }

    public void dealAll(){
        System.out.println("Dando as cartas, + "+cardsInHand + " carta para cada jogador por vez");
        Card[][] hands = new Card[playerCount][cardsInHand];
        for (int i = 0, cardIndex = 0; i< playerCount; i++){
            for(int j = 0; j < cardsInHand; j++){
                //dando 1 carta para cada jogador por vez
                hands[i][j] = deck.get(cardIndex++);
            }
        }

        int playerIndex = 1;
        for (Card[] hand : hands){
            cardGameHands.add(new CardHand(playerIndex++, Arrays.asList(hand)));
        }

//        System.out.println("Players Hands");
//        for ( int i = 0; i < playerCount; i++){
//
//            System.out.println("Player " + (i+1));
//            for (int j = 0; j < cardsInHand; j++){
//
//                System.out.println(hands[i][j]);
//            }
//
//        }



    }



}
