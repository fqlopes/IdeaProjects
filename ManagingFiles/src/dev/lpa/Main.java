package dev.lpa;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {

//        File oldFile = new File("students.json");
//        File newFile = new File("student-activity.json");
//
//        if( oldFile.exists()){
//            oldFile.renameTo(newFile);
//            System.out.println("File renamed successfully!");
//
//        } else {
//            System.out.println("The file doesn't exist!");
//        }

//        Path oldPath = Path.of("students.json");
//        Path newPath = Path.of("files/student-activities.json");
//
//        try {
//            Files.createDirectories(newPath.subpath(0, newPath.getNameCount() - 1));
//            Files.move(oldPath,newPath);
//            System.out.println(oldPath + " ---> " + newPath);
//            System.out.println("Path renamed successfully!");
//        } catch (IOException e){
//            System.out.println("Yeah, we know.");
//        }

        Path fileDir = Path.of("files");
        Path resourceDir = Path.of("resources");
        try {
            recurseDelete(resourceDir);
            recurseCopy(fileDir, resourceDir);
            System.out.println("Directory copied to " + resourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("files//student-activities.json"));
            PrintWriter writer = new PrintWriter("students-backup.json")) {
            reader.transferTo(writer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String urlString = "https://brasilapi.com.br/api/feriados/v1/2025";
        URI uri = URI.create(urlString);
        try (var urlInputStream = uri.toURL().openStream()) {

            urlInputStream.transferTo(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path jsonPath = Path.of("feriadosBR.txt");
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             var writer = Files.newBufferedWriter(jsonPath)) {
            reader.transferTo(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (var reader = new InputStreamReader(uri.toURL().openStream());
             PrintWriter writer = new PrintWriter("feriadosBR.csv")) {
            reader.transferTo(new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {

                    String jsonString = new String(cbuf, off, len).trim();
                    jsonString =jsonString.replace('[', ' ').trim();
                    jsonString = jsonString.replaceAll("\\]", "");
                    jsonString = jsonString.replaceAll(",", ",\n");
                    writer.write(jsonString);
                }

                @Override
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override
                public void close() throws IOException {
                    writer.close();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void recurseCopy(Path source, Path target) throws IOException {
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        if(Files.isDirectory(source)) {
            try (var children = Files.list(source)) {
                children.toList().forEach(path -> {
                    try {
                        Main.recurseCopy(path, target.resolve(path.getFileName()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

    }

    public static void recurseDelete(Path target) throws IOException {

        if(Files.isDirectory(target)) {
            try (var children = Files.list(target)) {
                children.toList().forEach(path -> {
                    try {
                        Main.recurseDelete(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        Files.delete(target);
    }
}
