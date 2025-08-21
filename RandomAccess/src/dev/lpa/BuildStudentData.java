package dev.lpa;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This class objective is to build the data file and index
public class BuildStudentData {


    public static void build(String datFileName, boolean separateIndex) {

        //Creating the path of our file
        Path studentJson = Path.of("students.json");

        //Creating a file name from the argument passed, to concatenate the dot extension
        String dataFile = datFileName + ".dat";

        //Creating a map type that keys the id and gives the position as value
        Map<Long, Long> indexedIds = new LinkedHashMap<>();

        try {
            //If the dat file, exists, we will delete it
            Files.deleteIfExists(Path.of(dataFile));

            //Reading the contents of the file, and assigning a type string to it
            String data = Files.readString(studentJson);

            //The json students record are wrapped in a square bracket ([]), so we'll manipulate to remove it
            data = data.replaceFirst("^(\\[)", "").replaceFirst("(\\])$", "");

            //Splitting the records, using the lineSeparator method of System
            var records = data.split(System.lineSeparator());

            //Printing the number of records in the console
            System.out.println("# of records = " + records.length);

            //Code to derive the starting position. This is the position were we start the data output
            //Since our map takes 2 Long values, each with 8 bytes, each entry takes 16 bytes.
            //we add 4 bytes to print the record count, at the start of the file.
            long startingPos = separateIndex ? 0 :  4 + (16L * records.length);

            //To create our index, we need to extract the student's ID from each record
            //A way to do this, is with RegEx Pattern, in the Matcher class
            Pattern idPattern = Pattern.compile("studentId\":([0-9]+)");

            //Using try-with-resources, for its autoclose features
            //The RandomAccessFile constructor takes 2 arguments: The file name, and it's properties
            try (RandomAccessFile ra = new RandomAccessFile(dataFile, "rw")) {

                //Calling seek while also leaving enough space to print our indexed data
                ra.seek(startingPos);

                //Looping through the records of the json file
                for (String record: records) {

                    //Matching each record to our pattern
                    Matcher m = idPattern.matcher(record);

                    //Looking up for the first match
                    if(m.find()) {

                        //If we find a match, we can get the studentId from group 1 we set in our RegEx
                        long id = Long.parseLong(m.group(1));

                        //Adding this ID as the key to the indexed Map,
                        //and the current file pointer as the position of the record in the data file
                        indexedIds.put(id, ra.getFilePointer());

                        //We use writeUTF to print the record to the file:
                        ra.writeUTF(record);
                    }
                }
                writeIndex(
                        (separateIndex) ? new RandomAccessFile(datFileName + ".idx", "rw"):
                        ra, indexedIds);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Code to print the record count, and the index data
    //It will the the RandomAccessFile to be written to, and the indexMap as arguments
    private static void writeIndex (RandomAccessFile ra, Map<Long, Long> indexMap) {

        try {
            //Making the index always point to the start of the file
            ra.seek(0);

            //Writing the size of the map, which should be equal to the number of records
            ra.writeInt(indexMap.size());

            //Looping through the map entries
            for (var studentIdx : indexMap.entrySet()) {

                //Writing the key and the values as Long, each write taking 8 bytes
                ra.writeLong(studentIdx.getKey());
                ra.writeLong(studentIdx.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
