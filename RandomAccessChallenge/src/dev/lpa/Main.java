package dev.lpa;

//RandomAccessChallenge

//1- Open a RandomAccessFile class with appropriate permissions.
//2- Load the employee index into memory.
//3- List your employee IDs in order.
//4- Retrieve an Employee Record from the file, using employeeId, to locate the position of that record in the file
//5- Print the employee record information to the console
//6- Update the selected Employee's salary in the file
//7- Retrieve the record again, and print the information to the console, confirming the salary change persisted

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.invoke.WrongMethodTypeException;
import java.util.*;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//We create a record, that matches our dat
record Employee (int employeeId, String firstName, String lastName, double salary){};



public class Main {

    //Making a our map index
    //Integer for the employeeId and Long for the file position
    private static Map<Integer, Long> indexedIds = new HashMap<>();

    //Static initializer, to load up indexes before it runs
    static {
        //Storing the number in records
        int recordsInFile = 0;

        //try-with-resources for RandomAccessFile, opening the file
        try (RandomAccessFile ra = new RandomAccessFile("employees.dat", "r")) {

            //We start by reading the first 4 bytes, which has the integer   record count in it
            recordsInFile = ra.readInt();
            System.out.println("There are # records in the file: " + recordsInFile);

            //We loop using the total record amount
            for (int i = 0; i < recordsInFile; i++) {

                //Reading the int as the ID and long as the file pointer
                indexedIds.put(ra.readInt(), ra.readLong());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        //As before, instantiating a new RandomAccessFile file
        try (RandomAccessFile ra = new RandomAccessFile("employees.dat", "rw")) {

            //Letting the user search for our employee ID
            Scanner scanner = new Scanner(System.in);

            //Getting the file position in the map


            //This list will be used to help prompt the user for a valid employee id
            //Creating a list of Employee IDs from the indexed map, and sorting it
            List<Integer> ids = new ArrayList<>(indexedIds.keySet());
            Collections.sort(ids);

            while (true) {
                System.out.println(ids);
                System.out.println("Enter a valid id option, or 0 to quit");
                if (!scanner.hasNext()) break;
                int employeeId = Integer.parseInt(scanner.nextLine());
                if (employeeId < 1){
                    break;
                }
                //Making sure that the user is actually giving a valid Id
                if (!ids.contains(employeeId)){
                    continue;
                }

                //If the code reaches this point, we have a valid ID from the user
                Employee e = readRecord(ra, employeeId);
                System.out.println("Enter new salary, nothing if no change");

                //Its good practice to deal with user inputs inside a try-catch,
                //to better deal with bad data inputs
                try {
                    double salary = Double.parseDouble(scanner.nextLine());
                    ra.seek(indexedIds.get(employeeId) + 4);
                    ra.writeDouble(salary);
                    readRecord(ra, employeeId);
                } catch (NumberFormatException ignore) {

                }



            }

        } catch (IOException e ){
            throw new RuntimeException(e);
        }

    }


    //Method to get a record in the file, instantiating a Employee record
    private static Employee readRecord(RandomAccessFile ra, int employeeId) throws IOException{

        //Reading our record
        ra.seek(indexedIds.get(employeeId));
        int id = ra.readInt();
        double salary = ra.readDouble();
        String first = ra.readUTF();
        String last  = ra.readUTF();

        //Creating the Employee instance with our gathered data
        Employee e = new Employee(id, first, last, salary);
        System.out.println(e);
        return e;
    }
}
