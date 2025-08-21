package study.self;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        Path deepestFolder = Path.of("public/assets/icons");

        try {
            createDirectoriesAndIndexes(deepestFolder);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDirectoriesAndIndexes(Path startingPath) throws IOException {

        if (!Files.exists(startingPath)) {
            Files.createDirectories(startingPath);
        }

        Path indexFile = startingPath.resolve("index.txt");
        if (!Files.exists(indexFile)) {
            try (Stream<Path> contents = Files.find(startingPath, Integer.MAX_VALUE, (p, atr) -> true)) {

                String fileContents = contents.map(path -> path.toAbsolutePath().toString())
                        .collect(Collectors.joining(
                                "\n",
                                "Generated! " + System.lineSeparator(),
                                "\nCreated at: "  + LocalDateTime.now() + System.lineSeparator()

                        ));
                Files.writeString(indexFile, fileContents);
            }
        } else {
            System.out.println("File already exists!");
        }

    }


    public static void generateDirectories(Path startingPath) throws IOException {

        //creating the index.txt using a given Path
        Path indexFile = startingPath.resolve("index.txt");
        try (Stream<Path> contents = Files.find(startingPath, Integer.MAX_VALUE,
                (p, atr) -> true)) {

            String fileContents = contents.map(path -> path.toAbsolutePath().toString())
                    .collect(Collectors.joining(
                            System.lineSeparator(),
                            "Directory Contents" + System.lineSeparator(),
                            System.lineSeparator() + "Generated " + LocalDateTime.now()));
            Files.writeString(indexFile,fileContents, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);





        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
