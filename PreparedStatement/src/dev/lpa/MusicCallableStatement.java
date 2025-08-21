package dev.lpa;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Map;
import java.util.stream.Collectors;

public class MusicCallableStatement {
    //constants to represent the column index of the data, in the csv file
    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    //The stored procedure will assign the track number in order, based on the order of the songs (added)
    //We will set this as a Map where (K -> String as Key V -> Nested Map as Value)

    public static void main(String[] args) {

        Map<String, Map<String, String>> albums = null;

        //Reading lines from a csv file using a stream of strings using the lines method
        //Using streams makes passing the data to the stored procedure easier
        //When calling Files.lines() we should use try-with-resources, so the stream gets closed

        try (var lines = Files.lines(Path.of("NewAlbums.csv"))) {

            //Setting the stream pipeline
            //We split each line by the comma (virgula) using the map operation, lambda splits the string
            //This will transform a stream of strings, to an array of strings.
            albums = lines.map(s -> s.split(","))
                    //We collect this data into a map, with Collectors.groupingBy
                    .collect(Collectors.groupingBy
                            //The first level is grouped by the artist
                            (s -> s[ARTIST_COLUMN],
                                    //The next grouping is the album
                                    Collectors.groupingBy(s -> s[ALBUM_COLUMN],
                                            //The last grouping is the song titles
                                            Collectors.mapping(s -> s[SONG_COLUMN],
                                                    //Collectors joining for it's StringJoiner capability, with the goal
                                                    //of making the output look like a JSON array
                                                    Collectors.joining("\",\"",
                                                            "[\"",
                                                            "\"]")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Printing to check the results
        //The key is the artist name, and the value is the artist's albums
        albums.forEach((artist, artistAlbum) -> {
            //We then loop through the nested map, which the key is the albumName, and the value is the songTitle
            artistAlbum.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();

        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("music");

        //This store procedure takes 3 input parameters, but doesn't return any data back
        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {
/*
            //Executing stored procedure, using a CallableStatement
            //We use the method prepareCall, passing a SQL string.
            //To execute a procedure in the database, we usually use the keyword CALL, followed by the procedure name
            CallableStatement cs = connection.prepareCall(
                    "CALL music.addAlbumInOutCounts(?,?,?,?)");


            //CallableStatements are also parsed and compiled, which means they can be re-used

            //We use our albums map, looping through keys and values
            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);
                        cs.setInt(4, 10);
                        //Getting data back from CallableStatements with 2 steps:
                        // 1- Before executing, we register in our code so it knows there's an OUT parameter
                        cs.registerOutParameter(4, Types.INTEGER);
                        // 2- Retrieve data from the statement, after it's executed
                        cs.execute();
                        System.out.printf("%d songs were added for %s%n", cs.getInt(4), album);
                    } catch (SQLException e) {
                        //Printing the specific vendor error
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });
*/

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);

            CallableStatement csf = connection.prepareCall("{ ? = CALL music.calcAlbumLength(?) }");
            csf.registerOutParameter(1,Types.DOUBLE);

            albums.forEach((artist, albumMap) -> {
                albumMap.keySet().forEach((albumName) -> {
                    try {
                        csf.setString(2, albumName);
                        csf.execute();
                        double result = csf.getDouble(1);
                        System.out.printf("Length of %s is %.1f%n", albumName, result);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
