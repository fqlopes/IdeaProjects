package dev.lpa.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

//This code offers a  server capable of processing multiple clients and multiple client requests, with a single thread.
//Powered by non-blocking IO
//Disadvantage: Not scalable, if requests/responses contains large amounts of data or fielding large number of requests
//Polling: continually checking for new connections and new requests
public class SimpleServerChannel {
    public static void main(String[] args) {

        //This method constructs a new server socket channel, which acts as a gateway to listen to incoming connections
        //The channel establishes an underlying internal socket object.

        //We instantiate through the static method open, which will act as a gateway to listen to incoming connections
        //Before it can listen for clients requests, we must bind it to a host and it's port
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            //We access internal sockets of client and server using the socket accessor methods
            serverChannel.socket().bind(new InetSocketAddress(5000));

            //To make a socket channel non-blocking, any evocations must be made before executing methods that block it
            serverChannel.configureBlocking(false); //accept method no longer blocks

            System.out.println("The server is listening on port " + serverChannel.socket().getLocalPort());

            List<SocketChannel> clientChannels = new ArrayList<>();

            while(true) {
//                System.out.println("Waiting to connect to another client");
                //Getting a socket from a client based on our server socket
                SocketChannel clientChannel = serverChannel.accept();

                if (clientChannel != null) {
                    //When client connects, we add to the list
                    clientChannel.configureBlocking(false);
                    clientChannels.add(clientChannel);
                    System.out.printf("Client %s connected%n", clientChannel.socket().getRemoteSocketAddress());
                }
                //The code that reads the requests of the client use buffers to pass data around
                //We define the capacity of the buffer
                //ByteBuffer is the most commonly used buffer when working with network data
                //ByteBuffer is also instantiated through a static method, allocate. it takes a capacity number of bytes
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                for(int i = 0; i < clientChannels.size(); i++){

                //This channel references the clientChannel
                SocketChannel channel = clientChannels.get(i);
                //To get the client's request data, we call the read method, passing the appropriate channel,
                //which takes a buffer instance.
                //Data gets transferred from the buffer from the channel
//                System.out.println("Waiting on client request data");
                int readBytes = channel.read(buffer);


                //The number of bytes will never be greater than the capacity, but it can be less
                if(readBytes > 0) {
                    //Unlike an InputStream buffer, which is managed by the stream,
                    //we have direct access and control over the buffer used by channels.
                    //This means we have to manually manipulate buffers
                    //Once data is in the buffer(channel has written), we want to read from it.
                    //To do this, we call the flip method on the buffer.
                    //This "flips" the buffer state: from writeable to readable.
                    buffer.flip();

                    //To write data to a channel, it must use a buffer
                    //A way to achieve this is to using the static method wrap, on a specific buffer
                    //Since the buffer is made of bytes, we call getBytes on our string
                    channel.write(ByteBuffer.wrap("Echo from server: ".getBytes()));

                    //We can now iterate through the data in the buffer created for the client request data
                    //To start iterating, we call the method hasRemaining, to continue to write data
                    //while we still have data to pass
                    while (buffer.hasRemaining()){

                        channel.write(buffer);
                    }
                    //Once done, we clear the buffer
                    buffer.clear();
                } else if (readBytes == -1) {
                    System.out.printf("Connection to %s was lost%n", channel.socket().getRemoteSocketAddress());

                    //manually closing the channel
                    channel.close();
                    clientChannels.remove(i);
                }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Server exception! Message: \n" + e.getMessage());
        }
    }
}
