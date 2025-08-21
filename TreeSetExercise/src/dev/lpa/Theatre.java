package dev.lpa;

import com.sun.source.tree.Tree;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Theatre {

    private String theatreName;
    private int seatsInRow;
    NavigableSet<Seat> seats;

    public Theatre(String theatreName, int numberOfRows, int totalSeatsNumber){

        this.theatreName = theatreName;
        this.seatsInRow = totalSeatsNumber/numberOfRows;

        seats = new TreeSet<>();
        int seatIndex = 1;
        for (int i = 0, charIndex = 0; i < totalSeatsNumber; i ++){

            seats.add(new Theatre.Seat((char)(charIndex +'A'),  seatIndex++));
            if ( (i+1) % numberOfRows == 0){
                charIndex++;
                seatIndex =1;
            }
        }
    }

    public void printSeatMap(){
        String separator = "-".repeat(90);
        System.out.printf("%1$s%n%2$s%n%1$s%n",separator,this.theatreName);

        int count = 0;
        for (Seat seat : seats){
            String seatDisplay = seat.reserved ? seat.seat + "(•) " : seat.seat + " ";
            System.out.printf("%-12s", seatDisplay);

            if ((++count) % seatsInRow == 0 ){ //  count++ =/= ++count --> operator ++ adds 1 first to count
                System.out.println();
                count = 0;
            }

        }
    }

    public String reserveSeat (char row, int seatNumber){
        //sets don't have a get method
        Seat request = new Seat (row, seatNumber);
        Seat existTest = seats.floor(request);

        if ( existTest != null && existTest.equals(request)){
            if (!existTest.reserved) {
                System.out.println("Seat reserved =  " + request);
                existTest.reserveSeat();
                return existTest.toString();
            } else {
                System.out.println("\nNIGGA THIS PLACE WAS TAKEN ALREADY GET LOST");
            }
        }


        return null;
    }


    public boolean validateBulk (int count, char first, char last, int min, int max){

        //count maior que zero, ter assentos, verificar se a contagem é possivel
        boolean validate = (count > 0 && count <= seatsInRow && (max - min + 1) > count);
        return validate;
    }

    public Set<Seat> reserveBulk (int count, char minChar, char maxChar, int minSeat, int maxSeat){

        boolean validation =validateBulk(count, minChar, maxChar, minSeat, maxSeat);
        if (!validation){
            System.out.println("THIS AIN'T A GAME NO MORE NIGGA,TAKE HEED AND BE SERIOUS");
            return null;
        }
        System.out.println("AllRIGHT BABY BUNNY YOU GIVEN ME SOME REAL ELEMENTS TO PROCESS LET ME DO THIS FOR YA");

        Set<Seat> selectedSeats = new TreeSet<>();

        for (char cLoop = minChar; cLoop < maxChar; cLoop++){
            NavigableSet<Seat> sideSeats = seats.subSet(new Seat (cLoop, minSeat), true, new Seat (cLoop, maxSeat), true);

            int index = 0;
            Seat first = null;

            for (Seat current : sideSeats){
                if (current.reserved){
                    index = 0;
                    continue;
                }

                first = (index == 0) ? current : first;
                if (++index == count){
                    selectedSeats = sideSeats.subSet(first, true, current, true);
                    break;
                }
            }
        }

        Set< Seat> reservedSeats = null;
        if (selectedSeats != null){
            selectedSeats.forEach(seat -> seat.reserved = true);
            reservedSeats = new TreeSet<>(selectedSeats);
        }
        return reservedSeats;


    }

    class Seat implements Comparable<Seat> {

        private String seat;
        private boolean reserved;
        public Seat (char row, int seatNo){
            this.seat = "%c%03d ".formatted(row,seatNo).toUpperCase();
        }
        public  void reserveSeat () {
           if (!this.reserved){
               this.reserved = true;
           }
        }

        @Override
        public int compareTo(Seat o) {
            return this.seat.compareTo(o.seat);
        }

        @Override
        public String toString(){
            return this.seat;
        }

        @Override
        public final boolean equals(Object object) {
            if (!(object instanceof Seat seat1)) return false;

            return seat.equals(seat1.seat);
        }
    }
}

