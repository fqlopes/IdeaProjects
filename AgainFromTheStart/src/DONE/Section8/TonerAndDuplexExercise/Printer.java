package DONE.Section8.TonerAndDuplexExercise;

public class Printer {

    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;


    public Printer (int tonerLevel, boolean duplex){
        if (tonerLevel < 0 || tonerLevel > 100){
            tonerLevel = -1;
        }
        this.tonerLevel = tonerLevel;
        this.pagesPrinted = 0;
        this.duplex = duplex;
    }

    public int addToner (int tonerAmount){
        if (tonerAmount > 0 && tonerAmount <= 100){
            if(tonerAmount + tonerLevel <= 100){
                this.tonerLevel += tonerAmount;
                return this.tonerLevel;
            }
        }
        return -1;
    }

    public int printPages (int pages){
        int pagesToPrint = pages;

        if (duplex){
            this.pagesPrinted += pagesToPrint/2 + pagesToPrint%2;
            return pagesToPrint;


        } else {
            this.pagesPrinted += pagesToPrint;
            return pagesToPrint;
        }
    }

    public int getPagesPrinted(){
        return pagesPrinted;
    }

}
