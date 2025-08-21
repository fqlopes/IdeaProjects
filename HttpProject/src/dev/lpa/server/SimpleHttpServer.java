package dev.lpa.server;

//Java comes with a Multi-Threaded HTTP Server that can be used out of the box

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.net.HttpURLConnection.HTTP_OK;

public class SimpleHttpServer {

        private static long visitorCounter = 0;

    public static void main(String[] args) {

        //We start with a HttpServer type object, instantiated passing a InetSocketAddress instance

        try {
            //HTTP Server Instance
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            //Once we have HTTP instance, the next step is to create HTTP contexts
            //HTTP Contexts represents a mapping from a URI path to a code that will handle the request
            //This code is referred to as exchange handler

            //Once created, all requests received by the server for the path,
            //will be handled by calling the given handler object, the exchange.
            //The context is identified by the path
            //The initial context is the root, and the handler can be a lambda expression
            server.createContext("/", exchange -> {

                //The first thing we do, is get the request method
                String requestMethod = exchange.getRequestMethod();
                System.out.println("Request method: " + requestMethod);

                String data = new String(exchange.getRequestBody().readAllBytes());
                System.out.println("Body data: " + data);

                //Adding a simple homepage to our server
                Map<String, String> parameters = parseParameters(data);
                System.out.println(parameters);

                exchange.getRequestHeaders().entrySet().forEach(System.out::println);

                if(requestMethod.equals("POST")) {
                    visitorCounter++;
                }

                String firstName = parameters.get("first");
                String lastName = parameters.get("last");
                String response = """
                        <html>
                            <body>
                                <h1>Hello World from My Http Server</h1>
                                <p>Number of visitors who signed up = %d<p>
                                <form method="post">
                                    <label for="first">First Name:</label>
                                    <input type="text" id="first" name="first" value="%s">
                                    <br>
                                    <label for="last">Last Name:</label>
                                    <input type="text" id="last" name="last" value="%s">
                                    <br>
                                    <input type="submit" value="Submit">
                            </form>
                            </body>
                        </html>
                        """.formatted(visitorCounter,
                        firstName == null ? "" : firstName,
                        lastName == null ? "" : lastName);

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                //To be able to send our homepage response back to the client,
                //we need to pass a byte array
                var bytes = response.getBytes();

                //We can also pass the encoding format, and if we don't,
                //it uses the system's default

                //It's necessary to send a response header, that includes at a minimum:
                //The Response Code -> HTTP_OK , and the length of the content
                exchange.sendResponseHeaders(HTTP_OK, bytes.length);

                //We get the Response Body from the exchange handler, and write into it
                exchange.getResponseBody().write(bytes);

                //Finally, we close the handler, with
                exchange.close();
            } );

            //Any code until here, won't do anything until the server is started
            server.start();
            System.out.println("The server is listening on port 8080...");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //This method parses parameter data and return it as a map
    private static Map<String, String> parseParameters (String requestBody) {

        Map<String, String> parameters = new HashMap<>();
        String[] pairs = requestBody.split("&");
        for (var pair : pairs){
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                parameters.put(keyValue[0],keyValue[1]);
            }
        }
        return parameters;
    }
}
