package dev.lpa;

public class BookingAgent {
    public static void main(String[] args) {

        int rows = 10;
        int totalSeats= 100;

        Theatre rodgersNYC = new Theatre("Richard Rogers", rows, totalSeats);



        bookSeats(rodgersNYC, 4, 'B',  3,10);
        bookSeats(rodgersNYC, 6, 'B','C',   3,10);
        bookSeats(rodgersNYC, 4, 'B',  1,10);
        bookSeats(rodgersNYC, 6, 'B','C',   1,10);
        bookSeats(rodgersNYC, 1, 'B','C',   1,10);
        rodgersNYC.printSetMap();

    }


    private static void bookSeat (Theatre theatre, char row, int seatNo){

        String seat = theatre.reserveSeat(row, seatNo);
        if( seat != null){
            System.out.println("Your seat was reserved" + seat);
            theatre.printSetMap();
        } else {
            System.out.println("Unable to reserve the seat ");
        }

    }

    private static void bookSeats (Theatre theatre, int tickets, char minRow, int minSeat, int maxSeat){

        bookSeats(theatre,tickets,minRow,minRow,minSeat,maxSeat);
    }

    private static void bookSeats (Theatre theatre, int tickets, char minRow, char maxRow, int minSeat, int maxSeat){

        var seats = theatre.reserveSeats(tickets,minRow,maxRow,minSeat,maxSeat);
        if (seats != null){
            System.out.println("FUCK YOU");
        } else {
            System.out.println("JUST COPYING AND STILL GETTING THINGS WRONG LMAO");
        }
    }
}
