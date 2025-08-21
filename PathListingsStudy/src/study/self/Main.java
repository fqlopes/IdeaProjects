package study.self;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Path path = Path.of("files/testing.txt"); //using a relative path to the file
        Path n0 = Path.of("target.txt");
        Path n1 = Path.of("n1/target.txt");
        Path n2 = Path.of("n2/n1/target.txt");
        Path n3 = Path.of("n3/n2/n1/target.txt");

        printPathInfo(path);
//        logStatement(path);

        Path anotherPath = Path.of("anotherTest.txt");
        System.out.println(anotherPath.getParent());

        myLogStatements(n0);
//        logStatement(anotherPath);
        readAttributes(n0);

    }

    private static void readAttributes (Path path) {

        try {
            var attributes = Files.readAttributes(path, "*");
            attributes.forEach((s, o) -> {
                System.out.printf("%s : %s%n", s,o);
            });
        } catch (IOException e){
            System.out.println("OOPS? WAS THAT ME? - Pudge");
        }
    }

    private static void printPathInfo (Path path){

        System.out.println("Exposing the Path's info");
        System.out.println("Path path: " + path);
        System.out.println("path.getFileName: " + path.getFileName());
        System.out.println("path.getParent: " + path.getParent());
        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute path: " + absolutePath);
        System.out.println("Absolute path root: " + absolutePath.getRoot());
        System.out.println("Root: " + path.getRoot()); // null indicates a relative path
        System.out.println("isAbsolute?: " + path.isAbsolute());
        System.out.println(absolutePath.getRoot());
        System.out.println("path.getNameCount: " + path.getNameCount());
        System.out.println("absolutePath.getNameCount: " + absolutePath.getNameCount());

        for (int i = 0; i < absolutePath.getNameCount(); i++){
            System.out.printf("%s%s%s%n", ".\t".repeat(i+1), ".\\", absolutePath.getName(i ));

        }
    }


    //using this method to create folders/files and deal with exceptions
    private static void logStatement (Path path) {

        try {
            System.out.println("Inside the try statement");
            Path parent = path.getParent();
            System.out.println("--->" + parent);
            System.out.println("Parent folder path created, checking existences");
            if(!Files.exists(parent)){
                System.out.println("Parent not found, creating one");
                Files.createDirectory(parent);
            }
            //The writeString takes a path, a csq (char sequence) and options taken from an ENUM StandardOperation
            Files.writeString(path, Instant.now() + "@time, hello!\n", StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void myLogStatements (Path path){

        try {
            Path parent = path.getParent();
            if (!Files.exists(path)){
                System.out.println("We checked, and the file doesn't exist, and there parent folders declared");

                if (parent != null) {
                    System.out.println("Creating parents");
                    Files.createDirectories(parent);
                }
                System.out.println("Creating file and writing");
//                Files.createFile(path);
                Files.writeString(path, Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))) +
                        " current time", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }


            if (Files.exists(path)) {
                System.out.println("File already exists!");
                System.out.println("Appending more data!");
                Files.writeString(path, Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))) +
                        " current time\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println("Something wrong!\nLOG:");
            var errors = e.getStackTrace();
            Arrays.stream(errors).forEach( error -> {
                System.out.println("\t" + error);
            });
        }
    }
}
