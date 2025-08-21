package dev.lpa.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//For the client, we just need to create a socket, because the client connects to a single server,
//which is configurable on the socket instance
public class SimpleClient {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost",5000)) {

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String requestString;
            String responseString;

            do {
                System.out.println("Enter a string to be echoed (sent to the server and back): ");
                requestString = scanner.nextLine();

                output.println(requestString);
                if(!requestString.equals("exit")) {
                    responseString = input.readLine();
                    System.out.println(responseString);
                }
            } while (!requestString.equals("exit"));

        } catch (IOException e) {
            System.out.println("Cliente error: " + e.getMessage());
        } finally {
            System.out.println("Client Disconnected");
        }
    }
}
