import javax.swing.plaf.basic.BasicTreeUI;

public class Main {
    public static void main(String[] args) {

        ComputerCase computerCase = new ComputerCase("Custom", "ASUS", "750");
        Monitor theMonitor = new Monitor("24inch GAMING", "ASUS", 24, "1920x1080");
        Motherboard motherboard = new Motherboard("B650ME", "ASUS", 4, 2, "3626");

        PersonalComputer thePC = new PersonalComputer("Custom", "Custom", computerCase, theMonitor, motherboard);

//        thePC.getMonitor().drawPixelAt(10, 10, "red");
//        thePC.getMotherboard().loadProgram("Windows 11");
//        thePC.getComputerCase().pressPowerButton();

        thePC.powerUp();
    }
}
