package dev.lpa;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/*      The Stream Source Challenge
*
*   Generate the bingo ball labels as 5 different streams, using different Stream creation
*   methods for each.
*
*   Assign each pipeline to a stream variable.
*   Concatenate the five streams together.
*   Apply the terminal operation, forEach, to the final concatenated stream, to print each label
*
*   These should be:
*
*   B1-B15
*   I16-I30
*   N31-N45
*   G45-G60
*   O61-O75
* */
public class Main {

    public static void main(String[] args) {

        int bingoSeed = 1;
        int safeSeed = bingoSeed;
        int increment = 15;
        var poolB = Stream.iterate(bingoSeed, i -> i <= (safeSeed + 14), i -> i+1)
                        .map(s -> "B"+s);
        bingoSeed += 14;

        var poolI = IntStream.rangeClosed(bingoSeed,bingoSeed + increment)
                        .mapToObj(i -> "I" + i);
        bingoSeed += increment;

        var poolN = Stream.generate(Main::getCounter)
                        .limit(15)
                        .map(i -> "G" +i );
        bingoSeed += increment;

        var poolG = Stream.iterate(bingoSeed +1, i -> i +1)
                        .limit(15)
                                .map(i -> "O" + i);
        bingoSeed += increment;

        var poolO = Stream.of("O61", "O62","O63","O64", "O65", "O66", "O67", "O68", "O69",
                "O70", "O71", "O72", "O73", "O74","O75");

        var poolBI = Stream.concat(poolB, poolI);
        var poolBIN= Stream.concat(poolBI, poolN);
        var poolBING = Stream.concat(poolBIN, poolG);
        var poolBINGO = Stream.concat(poolBING, poolO);

        poolBINGO.forEach(System.out::println);

    }

    static int counter = 31;
    public static int getCounter (){

        return  counter++;
    }

}
