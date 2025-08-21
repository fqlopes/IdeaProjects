package dev.lpa;

public class Main {
    public static void main(String[] args) {

        var baralho = Card.gerarDeck();
        Card.printDeck(baralho, "teste", 4);

    }
}
