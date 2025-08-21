package dev.lpa.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

//UDP Multi-threaded Server Channel
//It uses the UDPAudio client, which takes DatagramSockets to receive data
public class DatagramChannelServer {

    private static final int PORT = 5000;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {


        try(DatagramChannel channel = DatagramChannel.open()) {

            channel.bind(new InetSocketAddress(PORT));
            System.out.println("Server is listening on the port " + PORT);

            //Using a Selector, makes this code event-driven
            Selector selector = Selector.open();

            //Making the channel non-blocking
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);

            //A bytebuffer is necessary for exchanging data through the channel
            ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);

            //To listen to events, we set up a while loop
            while (true) {
                //This method blocks if there's nothing to do
                selector.select();

                //Getting the keys from the selector, which is a set of keys that gets returned
                //if any registered events have happened
                var selectedKeys = selector.selectedKeys();

                //We get an iterator from this set
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                //We add a nested while loop, that will iterate through each SelectionKey
                //This loop will iterate through our set, using our iterator
                while (keyIterator.hasNext()) {
                    //Getting the current SelectionKey
                    SelectionKey key = keyIterator.next();

                    //We remove it, then process it
                    keyIterator.remove();

                    if (key.isReadable()){
                        //If a key is readable, we get the channel that registers for that event
                        var registeredChannel = (DatagramChannel) key.channel();

                        //Clearing the buffer
                        buffer.clear();

                        //Getting the client's address
                        //This will return the client's address while alo writing incoming data the buffer
                        var clientAddress = registeredChannel.receive(buffer);
                        buffer.flip(); //From Writeable, to Readable

                        //Setting a byte array to read the buffer contents
                        byte[] data = new byte[buffer.remaining()];
                        buffer.get(data);

                        String audioFilePath = new String(data);
                        System.out.println("Client requested to listen to: " + audioFilePath);

                        //To make it multi-threaded we call our method to send data in a thread
                        new Thread(() -> sendDataToClient(audioFilePath, clientAddress, registeredChannel)).start();
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to send audio packets using DatagramChannel
    private static void sendDataToClient(String file, SocketAddress address, DatagramChannel channel) {

        //We start by creating a new byte buffer
        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);

        //Inside this try clause, we read data from the file using filechannel
        try (FileChannel fileChannel = FileChannel.open(Paths.get(file), StandardOpenOption.READ)) {
            while (true) {
                buffer.clear();
                int bytesRead = fileChannel.read(buffer);
                if (bytesRead == -1) {
                    break;
                }

                buffer.flip(); //From Writeable to Readable

                while (buffer.hasRemaining()) {
                    channel.send(buffer, address);
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(22);
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
