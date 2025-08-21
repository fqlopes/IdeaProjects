package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class PokerGame {


    private final List<Card> deck = Card.getStandardDeck();

    private int playerCount;

    private int cardsInHand;

    private List<PokerHand> pokerHands;

    private List<Card> remainingCards;


    public PokerGame (int playerCount, int cardsInHand){
        this.playerCount = playerCount;
        this.cardsInHand = cardsInHand;
        pokerHands = new ArrayList<>(cardsInHand);
    }

    public void startPlay(){

        Collections.shuffle(deck);
        Card.printDeck(deck);
        Collections.rotate(deck,deck.size()/2);
        Card.printDeck(deck);

        deal();
        pokerHands.forEach(System.out::println);

        Consumer<PokerHand> evalHand = PokerHand::evalHand;
        pokerHands.forEach(evalHand.andThen(System.out::println));

        int cardsDealt = playerCount * cardsInHand;
        int cardsRemaining = deck.size() - cardsDealt;

        //why would you code like this, ffs
        remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining, null));
        remainingCards.replaceAll(c -> deck.get(cardsDealt + remainingCards.indexOf(c)));
        Card.printDeck(remainingCards, "Remaining Cards", 2);

    }

    private void deal (){
        Card[][] hands = new Card[playerCount][cardsInHand];
        for(int deckIndex = 0, i = 0; i < cardsInHand; i++){
            for (int j = 0; j < playerCount ; j++){
                hands[j][i] = deck.get(deckIndex++);
            }
        }

        int playerNumber = 1;
        for (Card[] hand : hands){
            pokerHands.add(new PokerHand (playerNumber++, Arrays.asList(hand)));
        }
    }


}
