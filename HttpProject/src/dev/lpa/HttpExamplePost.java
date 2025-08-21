package dev.lpa;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;


public class HttpExamplePost {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:8080");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setRequestProperty("Accept", "application/json, text/html");

            connection.setReadTimeout(30000);

            //Adding information specific for posting data
            //To have data sent to the server, we need execute the method SetDoOutput to true,
            //which indicates that the connection will be used for output
            connection.setDoOutput(true);

            //we now set the content type to a specific value, application
            //This will the te server that the content is formatted in a very specific way
            connection.setRequestProperty("Content-Type", "application/x-www-form-url-urlencoded");
            String parameters = "first=Joe&last=Smith";
            int length = parameters.getBytes().length;
            connection.setRequestProperty("Content-length",String.valueOf(length));

            //We now need to populate request body with the parameter data
            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(parameters);
            output.flush();
            output.close();



            int responseCode = connection.getResponseCode();
            System.out.printf("Response code: %d%n", responseCode);

            if (responseCode != HTTP_OK) {
                System.out.println("Error reading web page " +url);
                return;
            }
            printContents(connection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
