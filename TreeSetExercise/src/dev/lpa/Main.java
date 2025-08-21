package dev.lpa;

public class Main {
    public static void main(String[] args) {

        //testing with a 3x3
        int numberOfRows = 5;
        int totalSeatsNumber= 25;

        Theatre test = new Theatre("Test",numberOfRows,totalSeatsNumber);
        reserveSeat(test, 'a', 1);
        reserveSeat(test, 'c', 3);
        reserveSeat(test, 'c', 1);
        reserveSeat(test, 'c', 1);

        reserveBulkSeats(test, 6, 'B', 'B',1, 5);
        reserveBulkSeats(test, 3, 'B', 'B',1, 5);
        test.printSeatMap();
    }

    public static void reserveSeat (Theatre theatre, char row, int seatNumber){

      String seatReserve = theatre.reserveSeat(row, seatNumber);
      if (seatReserve != null){
          System.out.println("seat reserved!" + seatReserve);
          theatre.printSeatMap();
      }

    }

    public static void reserveBulkSeats (Theatre theatre, int count, char minChar,char maxChar , int minSeat, int maxSeat){
        theatre.reserveBulk(count, minChar,maxChar, minSeat, maxSeat);

    }
}
