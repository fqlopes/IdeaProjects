package dev.lpa;

import java.util.List;

public class ParallelMain {
    public static void main(String[] args) {

        List<ParallelCard> deck = ParallelCard.getStandardDeck();
        ParallelCard.printDeck(deck);

        }

    }


