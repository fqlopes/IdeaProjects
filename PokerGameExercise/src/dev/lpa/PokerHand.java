package dev.lpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHand {

    private List<Card> hand;

    private List<Card> keepers;

    private List<Card> discards;

    private Ranking score = Ranking.NONE;

    private int playerNo;

    public PokerHand(int playerNo, List<Card> hand){
        hand.sort(Card.sortRankReversedSuit());
        this.hand = hand;
        this.playerNo = playerNo;
        keepers = new ArrayList<>(hand.size());
        discards = new ArrayList<>(hand.size());
    }

    public void setRank (int faceCount){

        switch (faceCount){
            case 4 -> score = Ranking.FOUR_OF_A_KIND;
            case 3 -> {
                    if (score == Ranking.NONE) score = Ranking.THREE_OF_A_KIND;
                    else score = Ranking.FULL_HOUSE;
            }
            case 2 -> {
                if (score ==Ranking.NONE) score = Ranking.ONE_PAIR;
                else if ( score == Ranking.THREE_OF_A_KIND) score = Ranking.FULL_HOUSE;
                else score = Ranking.TWO_PAIR;
            }
        }
    }

    public void evalHand(){
        List<String> faceList = new ArrayList<>(hand.size());
        hand.forEach(card -> faceList.add(card.face()));

        List<String> duplicateFaceCards = new ArrayList<>();

        faceList.forEach(card -> {
        if (!duplicateFaceCards.contains(card) && Collections.frequency(faceList, card) > 1){
            duplicateFaceCards.add(card);
            }
        });

        for (String duplicate : duplicateFaceCards){
            int start = faceList.indexOf(duplicate);
            int end = faceList.lastIndexOf(duplicate);
            setRank(end - start + 1);
            List<Card> sub = hand.subList(start, end +1);
            keepers.addAll(sub);
        }
    }

    private void pickDiscards(){

        List<Card> temp = new ArrayList<>(hand);
        temp.removeAll(keepers);
    }


    @Override
    public String toString(){
        return "%d. %-16s Rank:%d %-40s %s".formatted(playerNo, score, score.ordinal(),hand, (discards.size() > 0 ? "Discards: " + discards : ""));
    }
}
