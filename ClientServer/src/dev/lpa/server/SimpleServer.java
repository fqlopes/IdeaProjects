package dev.lpa.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



//The purpose of this code is just to echo back to the client.
//Available ports range from 0-65535 -> 2^16 = 65535 == 2 bytes == 16 bits
public class SimpleServer {

    public static void main(String[] args) {

        //IMPORTANT: try-with-resources don't auto-close socket connections. When closed, it just doesnt accept connections
        //Once we have a connection socket instance, we can wait for a connection to a client,
        //By calling the accept method on this variable
        //Opening a server socket
        try(ServerSocket serverSocket = new ServerSocket(5000)) {

            //blocks waiting for a connection. once connected we communicate by reading or passing information from the socket
            //Opening a client socket
            try (Socket socket = serverSocket.accept();) {
                System.out.println("Server accepts client connection");
                //We retrieve information from a client, using a BufferedReader
                //Variables to handle I/O (IOStreams)
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //PrintWriter for outputs
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                while(true) {
                    String echoString = input.readLine();
                    System.out.println("Server got request data: " + echoString);
                    if (echoString.equals("exit")) {
                        break;
                    }
                    output.println("Echo from server: " + echoString);
                }
            }
        }catch (IOException e){
            System.out.println("Server exception: " +e.getMessage() );
        }
    }
}
