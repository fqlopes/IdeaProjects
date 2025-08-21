import java.util.Scanner;

public class Lecture75 {

    public static void main(String[] args) {

        System.out.println("This is lecture 75");

        Scanner scanner = new Scanner (System.in);
        int counter = 1;
        double bigNumber = 0;
        double smallNumber = 0;


        do{
            System.out.println("Enter the "+ counter +" number");

            try {
                String number = scanner.nextLine();
                double inputNumber = Double.parseDouble(number);

                if (inputNumber > bigNumber){

                    bigNumber = inputNumber;
                    smallNumber = inputNumber;

                }

                if (inputNumber < smallNumber){

                    smallNumber = inputNumber;
                }


            }catch (NumberFormatException wrongdata){
                System.out.println("System halt");
                break;
            }




            counter++;

        }while (counter <= 3);


        System.out.println("The biggest number was "+ bigNumber + ", the smallest was "+ smallNumber + ".");




    }


}
