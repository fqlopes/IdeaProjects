import java.util.Scanner;

public class JavaMasterclassLecture74 {

    public static void main(String[] args) {
        System.out.println("This is the main statement.\nThis code represents lecture 74.");
        String test = "0";

        Scanner scanner = new Scanner (System.in);

        int counter = 1;
        int sum = 0;

        do{

            System.out.println("Enter number #: "+ counter);
            String nextNumber = scanner.nextLine();
            try {
                double number = Double.parseDouble(nextNumber);
                counter ++;
                sum +=number;

            } catch (NumberFormatException wrongType) {

                System.out.println("Wrong data type.");
            }
        } while ( counter <= 5);
        System.out.println("The total of sum of the "+ (counter -1) + " numbers are "+ sum);

    }


    public static boolean validNumber (int number){

        return number > 0 && number < 9 ;

    }

    public static void handeUserInput (){



    }

}

