package dev.lpa;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;


public class ASyncClientGet {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:8080");

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(url.toURI())
                    .header("User-Agent", "Chrome")
                    .headers("Accept", "application/json", "Accept", "text/html")
                    .timeout(Duration.ofSeconds(30))
                    .build();


            //This is a blocking method
            //To use asynchronous processing, we must wrap our HttpResponse<U> in another type,
            //the CompletableFuture<T>, which T is our HttpResponse<U>, resulting in a really ugly expression:
            //CompletableFuture<HttpResponse <String>>, which is the return type of sendAsync,
            //That also takes a HttpRequest, and a BodyHandler<T>

            HttpResponse<Stream<String>> response;
            CompletableFuture <HttpResponse<Stream<String>>> responseFuture =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofLines());

            //Checking if the future request is done already
            while ((response = responseFuture.getNow(null)) == null){
                System.out.print(". ");
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println();
            response = responseFuture.join();
            handleResponse(response);


        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Response processing done via a separate static method
    private static void handleResponse (HttpResponse<Stream<String>> response) {

        //Processing only if the response was OK
        if(response.statusCode() == HTTP_OK) {
            response.body()
                    .filter(s -> s.contains("<h1>"))
                    .map((s) -> s.replaceAll("<[^>]*>", "").strip())
                    .forEach(System.out::println);

        } else {
            System.out.println("Error reading response: " + response.uri());
        }

    }
}
