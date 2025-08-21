package dev.lpa;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //The most important usage of RandomAccessFile, are very large files,
    //that can be target for data modifications


    //Map for ID(Long) and its file pointer position(Long)
    private static Map<Long, Long> indexedIds = new LinkedHashMap<>();

    //Keeping track of how many records are in the file
    private static int recordsInFile = 0;

    //Static initializer to load the index
    //It's better to initialize once, with a static initializer,
    //rather than everytime this code is executed
    static {
        try (RandomAccessFile rb = new RandomAccessFile("student.idx", "r")) {
            loadIndex(rb,0);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

//        BuildStudentData.build("student", true);

        //Opening the RandomAccessFile passing the name and access mode code.
        //In this current case, "r" or "read" is enough, as we just want to read the data.
        try (RandomAccessFile ra = new RandomAccessFile("student.dat", "r")) {

            //We use our helper method, passing our RandomAccess and a position, which will be
            //starting at zero, because we want to read from the start of the file
//            loadIndex(ra, 0);

            //We will set a scanner, so that a user can request a record,
            //by inputting a student id
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a student ID or 0 to quit: ");

            //Setting the loop with our scanner, to get inputs until the user enters 0.
            while (scanner.hasNext()) {

                //Getting the studentId entered by the user
                long studentId = Long.parseLong(scanner.nextLine());
                if (studentId < 1) {
                    break;
                }

                //Getting the file position of the student from our map,
                //then using the seek method to go directly to that point
                ra.seek(indexedIds.get(studentId));

                //Reading the record:
                //Since we wrote using writeUTF, which is a method that includes the
                //length of the data that was written in the metadata.
                //Therefore, readUTF can first get that length,
                // and the only read the specified block into a string
                String targetedRecord = ra.readUTF();
                System.out.println(targetedRecord);
                System.out.println("Enter another student Id or 0 to quit: ");
            }

        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    //Code to load up the indexed data, and it takes a RandomAccessFile and a Index to read data
    private static void loadIndex(RandomAccessFile ra, int indexPosition) {
        try {
            //Getting the starting position
            ra.seek(indexPosition);

            //Reading the first data element
            recordsInFile = ra.readInt();
            System.out.println(recordsInFile);

            //Using a loop to determine how many times it should be read, and file positions
            for (int i = 0; i < recordsInFile; i++) {

                //Populating the map, using readLong to get the key and position
                indexedIds.put(ra.readLong(), ra.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
