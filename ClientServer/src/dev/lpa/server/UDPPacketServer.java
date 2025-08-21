package dev.lpa.server;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class UDPPacketServer {
    //We start by setting the port and the packet size as constants
    private static final int PORT = 5000;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {
        //We create datagram socket with try-with-resources
        try(DatagramSocket serverSocket =  new DatagramSocket(PORT)){
            //A byte variable for buffer to get data from the client
            byte[] buffer = new byte[PACKET_SIZE];
            System.out.println("Waiting for the client to connect...");
            DatagramPacket clientPacket =  new DatagramPacket(buffer,buffer.length);

            //A server receives a datagram from a client, when using UDP, and doesn't accept connections like TCP
            //All requests are received in the buffer
            serverSocket.receive(clientPacket);

            //This string constructor converts the byte array into a a string. it takes 3 variables:
            //buffer = our byte array, 0 = the starting position, clientPacket.getLength = final position
            String audioFileName = new String (buffer, 0, clientPacket.getLength());
            System.out.println("Client requested to listen to: " + audioFileName);

            //Getting information about received packets
            try {
                File audioFile = new File(audioFileName);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
                System.out.println(audioInputStream.getFormat());
            } catch (UnsupportedAudioFileException e){
                System.out.println(e.getMessage());
            }

            sendDataToClient(audioFileName, serverSocket, clientPacket);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    //This server will send the selected audio file data back to the client in datagram packets
    //To do this, we will build a static method, that is going to open the audio file requested
    //and send it bit by bit in datagram packets to the client
    private static void sendDataToClient (String file, DatagramSocket serverSocket, DatagramPacket clientPacket) {

        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);

        try (FileChannel fileChannel = FileChannel.open(Paths.get(file), StandardOpenOption.READ);) {
            //We can send packets back to the client by getting the client address and port information,
            //from the client packet we received earlier
            InetAddress clientIP = clientPacket.getAddress();
            int clientPort = clientPacket.getPort();

            //We set a while loop to read data from our channel
            while (true) {
                //Clearing the buffer before any write operations
                buffer.clear();
                //the read method will write into the buffer we supply it, until it reaches end of the of the file
                if (fileChannel.read(buffer) == -1) {
                    break;
                }

                buffer.flip(); //From writeable to readable

                while (buffer.hasRemaining()) {

                    byte[] data = new byte[buffer.remaining()];
                    //Reading data from the buffer, while populating the byte array with the get method
                    buffer.get(data);
                    //DatagramPacket to be sent to a client
                    DatagramPacket packet = new DatagramPacket( data, data.length, clientIP, clientPort);
                    //Sending
                    serverSocket.send(packet);
                }

                //Sleeping a bit between sent packets
                try {
                    TimeUnit.MILLISECONDS.sleep(22);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
