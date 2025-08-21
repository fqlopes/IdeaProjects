package dev.lpa.game;

import java.util.List;

public class CardHand {


    private int playerNo;

    private List<Card> playerHand;


    public CardHand (int playerNo, List<Card> playerHand) {

        this.playerNo = playerNo;
        this.playerHand = playerHand;
    }

    @Override
    public String toString(){
        return "Player %d%n %30s".formatted(playerNo, playerHand);

    }
}
