package dev.lpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebContent {

    public static void main(String[] args) {

        try {
//            URL url = new URL("http://example.com");
            URL url = new URL("https://jsonplaceholder.typicode.com/todos?id=5");
//            printContents(url.openStream());

            //Manual Connection
            //PS: The openConnection method doesn't actually establishes an actual network connection
            //Since the connection is done via two-steps, we are able to use the instance to configure
            //the connection before we actually connect to it.
            //Important Subclass: HttpURLConnection -> specific for web-pages
            URLConnection urlConnection = url.openConnection(); //Can be used in any type of network connection
            System.out.println(urlConnection.getContentType());
            urlConnection.getHeaderFields().entrySet().forEach(System.out::println);


            System.out.println(urlConnection.getHeaderField("Cache-Control"));

            //Starting the connection
            urlConnection.connect();
            printContents(urlConnection.getInputStream());


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
