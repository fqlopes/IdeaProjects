package DONE.Section5;

public class MegaBytesConverter {

    public static void printMegaBytesAndKiloBytes (int kiloBytes){
        if (kiloBytes < 0){
            System.out.println("Invalid Value");
        }else {
            int MegaInKilo = kiloBytes / 1024;
            int remainingKilo = kiloBytes % 1024;

            System.out.println(kiloBytes + " KB = " + MegaInKilo + " MB and " + remainingKilo + " KB");
        }
    }
}
