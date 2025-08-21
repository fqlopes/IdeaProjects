package dev.lpa;

import java.util.ArrayList;
import java.util.List;

//first, we set the house of the cards, we will use enum to do so


//the deck is declared as a record, so once it's created, it can't be changed
//it's generated using loops, and will use the enum number of elements to fill with cards
public record ParallelCard (CardHouse house, String face, int rank){

    public enum CardHouse {
        CLUB, DIAMONDS, HEART, SPADES; //these are the known houses when playing cards

        //setting a method to enumerate and make a shortcut for it's name, that being the first char letter of it
        public char getHouseImage (){
            char[] symbols = new char[]{9827, 9830, 9829, 9824};
            return symbols[this.ordinal()];
        }
    }

    //To generate numeric cards, we loop with the current card house, and start with card numbers from 2 to 10.
    public static ParallelCard getNumericCard (CardHouse cardHouse, int cardNumber){

        if (cardNumber > 1 && cardNumber <11){
            //from 2 to 10 will be created here
            return new ParallelCard (cardHouse, String.valueOf(cardNumber),cardNumber -2);
        }
        return null;
    }

    public static ParallelCard getFaceCard (CardHouse house, char abbreviation){

        //these are the cards Jack, Queen, King and Ace
        //they will be acquired in a loop later, when generating the whole deck
        //the loop runs through an array of chars of the same name J, Q, K,
        //this method receives the char and find inside the charIndex variable
        int charIndex = "JQKA".indexOf(abbreviation);
        //Getting a new deck will use the enum values index as to loop through these cards
        //if the charIndex is valid, it returns the current abbreviation (lettered cards)
        //and it's index, that starts after the card 10, at index 8, so we add +9
        if (charIndex > -1){
            return new ParallelCard(house, "" + abbreviation, charIndex + 9 );
        }
        return null;
    }

    public static List<ParallelCard> getStandardDeck(){

        //standard deck has 52 cards
        List<ParallelCard> deck = new ArrayList<>(52);

        //we start by looping through the card houses, where each house will have it's full set of cards
        //starting from 2 to 10, then going through Jack, Queen, King then Ace

        //we intend to generate the card of the decks like a matrix, using nested for loops
        //the first loop will set the house of the cards
        for (CardHouse house : CardHouse.values()){
            //looping from 2 to 10 to generate the cards with numerical value
            for (int i = 2; i <= 10 ;i++){
                //using the method we set previously to generate the cards, pass the method to achieve it
                deck.add(getNumericCard(house, i));
            }
            //once we're done looping from 2-10, we loop through the last cards with no numbers looping through char
            for (char c : new char[]{'J','Q','K','A' }){
                deck.add(getFaceCard(house, c));
            }
        }
        return deck;
    }

    //making a method to print everything without writing anything on main
    //this method is overloaded and will call the second method with the same name, passing some hard-coded values
    public static void printDeck (List<ParallelCard> deck){
        printDeck(deck,"Default Deck", 4);
    }


    public static void printDeck (List<ParallelCard> deck, String description, int rows){
        if (description != null){
            System.out.println(description);
        }
        int cardsInRow = deck.size()/rows; // 52 cards in 4 rows => 13 cards per row

        //using a for loop to place the cards, using the previous created method
        for (int i = 0; i < rows; i++){
            int startIndex = i * cardsInRow; // first loop == 0 * 13 = 0
            int endIndex = startIndex + cardsInRow; // first loop == 0 + 13 = 13;
            //we use subList method -> we isolate a portion of the list, even though it's still part of it,
            //and it makes sure that we're working with each card house separately

            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " ")); //lambda loop
            System.out.println();


        }
    }

    //to finalize it, we need a toString so that everything gets printed.

    @Override
    public String toString (){

        //we take a value in int then transform to a string with substring delimitation
        //since we're working with a string and not an array of strings, namely string[]
        //the substring method treats as if the string was an array of chars.
        //the first argument is inclusive, and is hard set as index 0
        //the second argument is exclusive, and is receiving through ternary operator either 2 or 1
        //for single digit values, we read the position 0 of the char array derived from the string
        //only when face equals 10, that we read the array from 0 to 1
        int index = face.equals("10")? 2:1;
        String faceString = face.substring(0, index);

        return "%s%c(%d)".formatted(faceString, house.getHouseImage(), rank);


    }
}
