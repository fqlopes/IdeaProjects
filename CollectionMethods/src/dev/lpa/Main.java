package dev.lpa;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Aces of Hearts", 1);

        List<Card> cards = new ArrayList<>(52); // list is empty, not even populated will null elements
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("cards.size() = " + cards.size());

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts); // populating 13 slots with a single card
        Card.printDeck(acesOfHearts, "Aces of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, 'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "Kings of Clubs", 1);

        Collections.addAll(cards, cardArray);
        Collections.addAll(cards, cardArray);
        Card.printDeck(cards, "Cards with ace Added", 2);

        //the copy method copies references, not the object itself
        Collections.copy(cards, kingsOfClubs);
        Card.printDeck(cards, "Card Collection copied", 2);

        //the copyof creates a new identical copy of an object
        cards = List.copyOf(kingsOfClubs); //a true copy, but unmodifiable list
        Card.printDeck(cards, "List of copied Kings", 1);

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        //randomized elements inside a list
        Collections.shuffle(deck); //randomizes each element with other elements that were present in the given List
        Card.printDeck(deck, "Shuffled deck", 4);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed deck", 4);

        //Sorting in collections has 2 types:
        //  TYPE 1: implement Comparable
        //  TYPE 2: don't implement Comparable -> Uses a Comparator (Functional interface)


        var sortingAlgorithm = Comparator.comparing(Card::rank).thenComparing(Card::suit);
        Collections.sort(deck, sortingAlgorithm);
        Card.printDeck(deck, "sorted deck", 13);

        Collections.reverse(deck);
        Card.printDeck(deck, " reverse sorted deck", 13);

        List<Card> kings = new ArrayList<>(deck.subList(4,8));
        Card.printDeck(kings , "kings in a deck", 4);

        List<Card> tens = new ArrayList<>(deck.subList(16,20));
        Card.printDeck(tens , "tens in a deck", 4);

        //indexOfSubList will search if a list is contained in another, returning the index of where it was found

        int subListIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("subListIndex = " + subListIndex); //searches for the identical copies and structures of data
        System.out.println("contains output = " + deck.containsAll(tens));

        //Collections.disjoint evaluates 2 lists and returns true if no common elements exists, false is they do
        boolean disjoint = Collections.disjoint(kings, tens);
        System.out.println("disjoint = " + disjoint);

        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);

        //binary search will take a list, the elements to be found, and a comparator
        //the elements in the list MUST be sorted, or it won't work
        deck.sort(sortingAlgorithm);
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
        System.out.println("foundIndex =" + foundIndex);
        System.out.println("foundIndex =" + deck.indexOf(tenOfHearts));
        System.out.println(deck.get(foundIndex));

        //Collections has a replaceAll method, but it's more limited than list's
        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB, 10);
        //replaceAll (list, old value, new value)
        Collections.replaceAll(deck, tenOfClubs, tenOfHearts);
        Card.printDeck(deck.subList(32,36), "tens row",1);
        //though it does what we ask, the method is a boolean, so it's better wrap it with a if statement
        Collections.replaceAll(deck, tenOfHearts, tenOfClubs);
        Card.printDeck(deck.subList(32,36), "tens row",1);

        if (Collections.replaceAll(deck, tenOfHearts, tenOfClubs)){
            System.out.println("Tens of hearts were replaced with tens of clubs");
        }else {
            System.out.println("no matches found in the list");
        }

        //frequency will check for duplicates
        System.out.println("Tens of clubs cards = " + Collections.frequency(deck, tenOfClubs));

        //Collections can give min and max values based on naturalOrder or given Comparator
        //the methods are .max and .min
        //they can work with or without the implementation of Comparable
        System.out.println("Best Card = " + Collections.max(deck, sortingAlgorithm));
        System.out.println("Worst Card = " + Collections.min(deck, sortingAlgorithm));

        var sortBySuit = Comparator.comparing(Card::suit).thenComparing(Card::rank);
        deck.sort(sortBySuit);
        Card.printDeck(deck, "Sorted by suit, rank", 4);


        List<Card>copied = new ArrayList<>(deck.subList(0,13));
        //rotate pushes the list indexes by a certain distance
        //if pushed 1, the original first element is now the second element
        //the last element is now the first element
        //pushed twice, the last two elements are now the first two. so on and so forth
        //also works with negative numbers of rotation,
        // where it moves the first element to the last
        Collections.rotate(copied,0);
        System.out.println("Unrotated list = " + deck.subList(0,13));
        System.out.println("Rotated list = " + 2 +  ": " + copied);

        //the swap method
        copied = new ArrayList<>(deck.subList(0,13));
        for (int i = 0; i < copied.size()/2; i++){
            Collections.swap(copied, i , copied.size() -1 - i);
        }
        System.out.println("Manual reverse: " + copied  );
    }
}
