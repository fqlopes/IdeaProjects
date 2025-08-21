package dev.lpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;


public class HttpClientGet {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:8080");
            //To use a HttpClient, the simplest way is to call a static method newHttpClient,
            //part of the HttpClient class, which creates a new instance with builder methods
            //and default settings
            HttpClient client = HttpClient.newHttpClient();

            //With the HttpClient, we first create a request object, then invoke a send method
            //on the client instance, passing the requested object

            //For the HttpRequest, we start calling the newBuilder method, chaining methods to it.
            //We can also set the header value and timeouts through this method chain
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(url.toURI())
                    .header("User-Agent", "Chrome")
                    .headers("Accept", "application/json", "Accept", "text/html")
                    .timeout(Duration.ofSeconds(30))
                    .build();

            //While using the HttpClient and HttpRequest classes, we specifically use the client instance,
            //invoking a send method that takes a request object.

            //Sending a GET message, we get a HTTP Response back, and the type of data
            //we get in this response is dependent on the type of body handler used
            //Here, it will be a String
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<Stream<String>> response = client.send(request, HttpResponse.BodyHandlers.ofLines());


            if (response.statusCode() != HTTP_OK) {
                System.out.println("Error reading the web page: " + url );
                return;
            }

            //With HttpClient, there no input stream to be dealt with,
            //unless specifically chosen with a different Body Handler
            //The response body can be printed, because we used the String type
//            System.out.println(response.body());

            response.body()
                    .filter(s -> s.contains("<h1>"))
                    .map((s) -> s.replaceAll("<[^>]*>", "").strip())
                    .forEach(System.out::println);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
