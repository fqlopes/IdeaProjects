package study.self;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Path path = Path.of("C:\\Gravity");
        System.out.println("pwd = " + path.toAbsolutePath());

        //try-with-resources!
//        try (Stream<Path> pathStream = Files.list(path)) {
//            pathStream.filter(Files::isDirectory).map(Main::listDir).forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("===========================================");

//        try (Stream<Path> pathStream = Files.walk(path, 5)) {
//            pathStream.filter(Files::isRegularFile).map(Main::listDir).forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try (Stream<Path> pathStream = Files.find(path, 5,
                (p, attr) -> attr.size() < 500)) {

            pathStream

                    .map(Main::listDir).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===========================================");


        try (FileReader myReader = new FileReader("text.txt")) {
            var anotherReader = new BufferedReader(myReader);
            var wtfIsThis = anotherReader.lines();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String listDir(Path path) {
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateField = Files.getLastModifiedTime(path);
            LocalDateTime modDT = LocalDateTime.ofInstant(dateField.toInstant(), ZoneId.systemDefault());
            return "%tD %tT %-15s %12s %-15s %-15s".formatted(modDT, modDT, (isDir ? "<DIR>" : ""), (isDir ? "" : Files.size(path)), path , Files.isRegularFile(path));
        } catch (IOException e) {
            e.printStackTrace();
            return path.toString();
        }
    }
}
