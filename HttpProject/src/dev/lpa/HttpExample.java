package dev.lpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;


public class HttpExample {
    //Some methods implicitly perform the connection step

    public static void main(String[] args) {

        try {
//            URL url = new URL("http://example.com/extra");
            URL url = new URL("http://localhost:8080");
            //This method will does the connection implicitly
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Setting the header of a HTTP client, which contains information about the request or a response
            connection.setRequestMethod("GET"); //GET is method in HTTP for a URL connection

            //Setting the User-Agent properties
            //The User-Agent tells the server what browser/script is being used
            //Setting it to mimic Firefox browser
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setRequestProperty("Accept", "application/json, text/html");

            //Timeout values are available
            connection.setReadTimeout(30000);

            //Check the server's response code. Makes a connection
            int responseCode = connection.getResponseCode();
            System.out.printf("Response code: %d%n", responseCode);

            //Some common codes are available as enum constants, static import
            if (responseCode != HTTP_OK) {
                System.out.println("Error reading web page " +url);
                return;
            }
            printContents(connection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //This method takes an InputStream and writes the contents on the console
    private static void printContents(InputStream is) {

        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ( (line = inputStream.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
