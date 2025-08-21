package study.self;

import javax.swing.text.BadLocationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {


/*  FILEFUCKERS 101 - JAVA I/O
*   SECTION 1: FUCKING EXCEPTIONS MATE
*
*   WE START BY LOOKING AT SOME WAYS JAVA WILL MAKE AND BREAK YOUR MIND WHILE TRYING TO WORK WITH FOLDERS AND FILES
*   THE LANGUAGE HAS 3 DISTINCT PACKAGES OF BABYLONIAN DEMONS, AND I WILL NOT FALL BEHIND BECAUSE IF SOME
*   SHITHEAD WAS CAPABLE OF DOING IT, I'M CAPABLE OF UNDERSTANDING IT. I'M NO LESS INTELLIGENT THAN MY HAIRLESS
*   MONKEYS BROTHERS AND SISTERS THAT CAME BEFORE ME.
*
*
*   CLEANSE DOUBT. PURGE IGNORANCE. KILL INCOMPREHENSION. */

        //Creating a variable to a file name
        String filename = "test.csv";
        testFile(filename);
        //Trying to get a path instance
        Path path = Paths.get(filename);
        //Looking at our path's methods:
        System.out.println("path.getRoot() : " +path.getRoot());
        System.out.println("path.getFileName() : " + path.getFileName());
        //getName generates an error at this point if the index is different from zero.
        System.out.println("path.getName(index = 0) : " + path.getName(0));
        System.out.println("path.getFileSystem() : " + path.getFileSystem());
        System.out.println("path.getParent() :" + path.getParent());
        System.out.println("path.getNameCount() : " + path.getNameCount());

        //Can I read from this fucker?
        //Im gonna try to make a list of strings, for each line inside that file
        //Turns out when trying to use the Files method readAllLines, i'll get an error, if I don't
        //deal with the Exceptions of IO shit. So we wrap it in a try-catch
        testFileFirstTimer(filename); // <- this method
        testFile(filename);
        testFile2(filename);
        testFile3(filename);
        separator();

        //If we check if it exists first, will we break free from the try-catch block?
        //I'll try to use the old File class, from JDK1
        File file = new File(filename);

        //Alright,simple as instantiating a class. Let's use if block to enforce checks of existence
        if (!file.exists()) {
            System.out.println("Doesn't exist, sorry!");
            return;
        } else {
            System.out.println("We found the file, now what? Awaiting orders");
        }

        //Let's try using the fileReader method, treated with try-catch blocks
        testFileAgain(filename);

        separator();

        testFile3(filename);


    }

    private static void testFileAgain (String filename) {

        Path path = Paths.get(filename);
        FileReader reader = null;
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e){
            System.out.println("File doesn't exist, can't go on!");
        }
    }

    //try with resources -> try()
    private static void testFile2 (String filename){
        try (FileReader reader = new FileReader(filename)) {
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
//            throw new RuntimeException(e); // will print errors
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    private static void testFile3 (String filename){
        try (FileReader reader = new FileReader(filename)) {
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IllegalArgumentException | NullPointerException baddata) {
            System.out.println("User printed bad data!" + baddata.getMessage());
        } catch (IOException e) {
            System.out.println("Why are trying so hard to fail?");
        }

    }

    private static void testFile (String filename) {
        Path path = Paths.get(filename);
        try {
            List<String> lines = Files.readAllLines(path);
        } catch (IOException e) {
            //throw new RuntimeException(e); //this gets called and thrown. and will execute at line 25 in main
        } finally {
            //finally was made to clean-up code, closing resources, freeing files, memory, etc
            System.out.println("Perhaps logging is useful, maybe...");
        }
    }

    private static void testFileFirstTimer (String fileName) {

        Path path = Paths.get(fileName);
        try {
            List<String> readAllLines = Files.readAllLines(path);
        } catch ( IOException e){
            System.out.println("\n********************");
            System.out.println("THE CATCH BLOCK");
            System.out.println("********************");
            System.out.println("\nSomething went wrong here.");
            System.out.println("Catching the IOException because mom told me so.");
            System.out.println("If I \"throw\", you can catch, :P ");
            System.out.println("**ERROR**");
            System.out.printf("IOException e, where e means: [%s]%n", e.fillInStackTrace().toString());
            System.out.println("We don't know nothing about, and we feel bad about it.\n");
        } finally {
            System.out.println("********************");
            System.out.println("THE FINALLY CLAUSE");
            System.out.println("********************");
            System.out.println("I AM THE MASTER OF MYSELF, AND I GET EXECUTED EITHER WAY");
            System.out.println("THE CODE WORKS? GOOD. I WAS THERE.");
            System.out.println("THE CODE DOESN'T WORK. TOO BAD, I WAS THERE TOO!");
            System.out.println("********************");
        }

    }

    static void separator(){System.out.printf("\n%s\n\n", "=".repeat(120));}
}
