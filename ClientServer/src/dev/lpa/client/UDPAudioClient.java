package dev.lpa.client;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPAudioClient {

    //Data and Server properties:
    private static final int SERVER_PORT = 5000;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {

        //the client doesn't need to include the port
        try (DatagramSocket clientSocket = new DatagramSocket()){

            //We identify a generic client request via byte array
            byte[] audioFileName = "AudioClip.wav".getBytes();

            //Passing data to the server in the form of a Datagram packet
            //DatagramPacket is constructed with 4 arguments:
            //1: the data itself -> the audio file
            //2: the data's length -> the audio file's length
            //3: the server's address -> in this case, the localhost
            //4: the server's port
            DatagramPacket packet1 = new DatagramPacket(
                    audioFileName, audioFileName.length, InetAddress.getLocalHost(), SERVER_PORT);

            //Data is sent via the clientSocket, using the send() method. It takes an argument which is our dtgPacket.
            clientSocket.send(packet1);

            playStreamedAudio(clientSocket);

        } catch (IOException | LineUnavailableException e){
            System.out.println(e.getMessage());
        }
    }

    //A method to treat data coming from the server and playing an audio
    private static void playStreamedAudio(DatagramSocket clientSocket) throws SocketException, LineUnavailableException {

        //We set a timeout on our clientSocket, so that if doesn't receive any data for that length of time,
        //it will timeout and shut down completely and cleanly.

        clientSocket.setSoTimeout(2000);

        //All this is specific to playing the audio
        //We use a Swing type, AudioFormat and it takes the audio's characteristics and properties as variables
        AudioFormat format = new AudioFormat(22050, 16, 1, true, false);

        //To play audio, we create a DataLine.Info variable
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        //The key to play an audio, is the SourceDataLine, and we point it AudioSystem.getLine to get it
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

        //The line then needs to opened and started
        line.open();
        line.start();

        //Now we need to get data from the server to play it
        //Once again, data coming from other hosts are stored in a buffer
        byte[] buffer = new byte[PACKET_SIZE];

        //A loop is made to continuously read data packets incoming from the server
        while (true) {
            try {
                //We prepare a DatagramPacket to receive data from the server
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                //We execute the receive method to get data from the server, on our clientSocket
                clientSocket.receive(packet);

                //The byte array (buffer) that is part of the packet, will be populated with audio from the server
                //We then can write this buffer's data to the SourceDataLine, which will play it
                line.write(buffer,0, packet.getLength());
            } catch (IOException e){
                System.out.println(e.getMessage());
                break;
            }

        }
        //The line data-stream must be closed manually when we break out of the loop
        line.close();

    }

}
