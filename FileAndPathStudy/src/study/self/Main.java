package study.self;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

interface Logger {
    void log (Object o);
}

public class Main {
    static Logger p = System.out::println;

    public static void main(String[] args) {
        p.log("Hello there");
        useFile("testfile.txt");
        System.out.println("=".repeat(90));
        usePath("pathfile.txt");

    }

    private static void useFile (String fileName) {

        File file = new File(fileName);
        boolean fileExits = file.exists();
        System.out.printf("%s %s%n", fileName, fileExits ? "exists" : "doesn't exist");
        //If the file exists, we will delete it, to create it again
        if (fileExits) {
            System.out.println("Deleting the file!");

            fileExits = !file.delete(); //if deletion succeeds, it returns true. a deleted file doesnt exists, so we flip to false and be factually true.
        }

        if(!fileExits) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
            System.out.println("File created!");
            if (file.canWrite()){
                System.out.println("File is writable!");
            }
        }

    }

    //This method will use the NIO package to create a new file
    private static void usePath(String fileName) {
        //like previously stated, we work with a path to the file, and not the file itself.
        Path path = Path.of(fileName);
        //NIO deals with files and paths separately, and we deal with files using the Files class and it's methods
        boolean fileExists = Files.exists(path);

        //the method used in the File class cannot be replicated in the NIO methods of dealing with files and folders.
        if (fileExists) {
            System.out.println("File Exists");
            System.out.println("Deleting the file!");
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            System.out.println("File doesn't exist");
            System.out.println("Creating one");
            try {
                System.out.println("We have successfully created a the file");
                Files.createFile(path);

                if (Files.isWritable(path)) {
                    System.out.println("File is writable!");
                    System.out.println("Writing");
                    //Here is how we write inside the file we just created, again using the Files class while
                    //directly refering to the path of our file.
                    Files.writeString(path, "I can see you.");
                }
                //We can also call the readAllLines method to read text from a file
                //The readAllLines returns a List of Strings, which in turn can be chained with a forEach:
                var aVar =Files.readAllLines(path);
                aVar.forEach(System.out::println);


            } catch (IOException e) {
                System.out.println("Something bad happaned, and a error occurred.");
            }

        }
    }
}
