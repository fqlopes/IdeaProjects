package dev.lpa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {

        String currentUser = System.getProperty("user.home");
        System.out.println(currentUser);
        System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());
//        String filename = currentUser + "/files/testing.csv";
        String filename = "files/testing.csv";
        File file = new File(new File("").getAbsolutePath(),filename);
        System.out.println("\n"+ file.getAbsoluteFile());
        if (!file.exists()){
            System.out.println("File doesn't exist.");
            return;
        }
        System.out.println("OK.");

        for(File f : File.listRoots()){
            System.out.println(f);
        }

        Path path = Paths.get("files/testing.csv");
        System.out.println(path);


        System.out.println(file.getAbsoluteFile());
        if (!Files.exists(path)){
            System.out.println("2. File doesn't exist.");
            return;
        }
        System.out.println("2. OK.");

    }

    private static void testFile (String filename){

        Path path = Paths.get(filename);
        FileReader reader = null;
        try {
//            List<String> lines = Files.readAllLines(path);
            reader = new FileReader(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Perhaps we shouldd start logging...");
        }
        System.out.println("File exists, moving on.");
    }

    private static void testFile2 (String filename){
        try (FileReader reader = new FileReader(filename)) {
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        } catch(NullPointerException | IllegalArgumentException badData){
            System.out.println("User has used bad data " + badData.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch(Exception e){
            System.out.println("Something unrelated and unexpected happe");
        }
        finally {
            System.out.println("Maybe i'd log something either way");

        }
        System.out.println("File exists and able to be used as a resource");
    }
}
