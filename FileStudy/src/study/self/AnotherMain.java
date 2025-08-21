package study.self;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

interface Printer {
    void log (Object o);
}

public class AnotherMain {
    public static void main(String[] args) {
        Printer p = System.out::println;

        File emptyFile = new File("");
        File emptyFileDot = new File(".");

        //Absolute Pathing to the Project's files, coming from the root directory of a system
        p.log("emptyFile.getAbsolutePath() :\n\t" + emptyFile.getAbsolutePath());
        System.out.println("emptyFileDot.getAbsolutePath() :\n\t" + emptyFileDot.getAbsolutePath());

        //The roots present in your system can be seen by the listRoots() method in File
        for (File f : File.listRoots()){
            System.out.println(f.getPath());
        }

        System.out.println("If we declare a new File with an empty string is means that :" +
                "\n START: System's Root END: Current project folder:");
        System.out.println("\tThe Absolute Path of a empty string is the project's folder path from the root :\n\t\t " +
                new File("").getAbsolutePath());

        //If the file is previous created, the commands can find the file
        String filenamePath = "myFolder/myFile.txt";
        String anotherFilePath = "anotherFile.txt";

        //As in Linux, if we declare a string path to a file,
        //if we start with a forward slash "/", it will be interpreted as root.
        //We should be very careful, since all OS's have different filesystems folder divisions.
        String notTheSamePath = "/myFolder/myFile.txt"; // sucks for linux

        Main.separator();
        p.log(new File(filenamePath).getAbsolutePath());
        newFileTester(filenamePath);
        Main.separator();
        p.log(new File(notTheSamePath).getAbsolutePath());
        newFileTester(notTheSamePath);
        Main.separator();

        p.log("The / stands for the root. See -> " + new File("/").getAbsolutePath());
        p.log("The empty string will return our project folder. See -> " + new File("").getAbsolutePath());
        p.log("The overloaded version of the File constructor, that takes a parent path, i wonder how low can it be.");

        File file = new File("/", filenamePath);
        p.log("The File named file, giving the parent as the root");
        p.log("Points to:\n\t" + file.getAbsolutePath());
        p.log("The parent value taken in the constructor must can only be 1 level above");
        p.log("Trying with again with a different file");
        File anotherFile = new File("/", anotherFilePath);
        p.log("Points to:\n\t" + anotherFile.getAbsolutePath());
        p.log("Watching the statements made me realize that the constructor takes face value only");
        p.log("Any kind of mistake naming the file, folder or the actual path may result in errors.");
        p.log("\nAnother way to point out the current folder to this overloaded method, is to pass a single dot");
        p.log("as the parent, which will mean : \n\t" + new File(".", filenamePath).getAbsolutePath());
        p.log("\nAnd to be extra spicy, we can nest a new File call to get the path without the dot showing");
        p.log("Check files to see how to do it, i will point out a pre-created existing file");
        File nestedCallFile = new File(new File("").getAbsolutePath(), anotherFilePath);
        p.log("Pointing to:\n\t" + nestedCallFile.getAbsolutePath());
        p.log("\texists? -> " +nestedCallFile.exists());

        p.log("The standard library io is hard, and confuse, we will try to learn nio from now on.");
        Main.separator();

        p.log("Using the File class, we would instantiate it, then perform methods on the file, while checking" +
                "permissions for operations.\nWorking with NIO, we will work with the Path to the file instead," +
                "and we begin by instantiating a type that implements the Path interface, which is present in NIO.");

        Path path = Paths.get("myFolder/myFile.txt"); // using a relative path
        Path anotherPath = Paths.get("anotherFile.txt");

        p.log("Time to check some methods.\nWhen working with paths, we use toAbsolutePath, then checking parents");
        p.log("\t" + path.toAbsolutePath() + "\n\t" + anotherPath.toAbsolutePath());
        p.log("\t" + path.getParent() + "\n\t" + anotherPath.getParent());

        p.log("Checking the path variable file existence:");
        if(!Files.exists(path)) {
            p.log("The file doesn't exist.");
        } else {
            p.log("The file exists.");
        }
        p.log("Checking the anotherPath variable file existence");
        if(!Files.exists(anotherPath)) {
            p.log("The file doesn't exist.");
        } else {
            p.log("The file exists.");
        }


    }

    private static void newFileTester (String filenamePath) {

        File preCreatedFile = new File(filenamePath);
        if (preCreatedFile.exists()){
            System.out.println("SUCCESS!");
            System.out.println("getPath -> START: current project folder END: current file");
            System.out.println("\tThe file was found at Path : \n\t\t" + preCreatedFile.getPath());
            System.out.println("getAbsolutePath -> START: System's root END: current file");
            System.out.println("\tThe file can also be found : \n\t\t" + preCreatedFile.getAbsolutePath());
        } else {
            System.out.println("FAILED!");
            System.out.println("The file was not found. Go figure!");
            System.out.println("RELATIVE PATH POINTED TO : \n\t" + preCreatedFile.getPath());
            System.out.println("ABSOLUTE PATH POINTED TO : \n\t" + preCreatedFile.getAbsolutePath());

        }

    }
}
