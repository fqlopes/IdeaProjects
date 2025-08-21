package dev.lpa.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelSelectorServer {

    public static void main(String[] args) {

        try(ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(5000));
            //For event-driven code, we want to set configureBlocking to false on our channels
            serverChannel.configureBlocking(false);

            //We get a selector by invoking the static method open, on the Selector class
            Selector selector = Selector.open();

            //Channels have a register method that lets us register which events we want to be notified,
            //linking it to the current channel
            //We call register, passing the selector and the operation this channel is interested in.
            //For a server socket channel, it will be to accept the event
            //To do that, we evoke enum constants present in the SelectionKey class.
            //It creates a selection key, which java refer as "Registration Token".
            //The selection key serves as a bridge between the channel and the program.
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            //When a channel becomes ready for any of its registered interests, the selector wakes up the
            //program, and provides the corresponding selection key which has the information about the event
            //and the channel that registered for that event.
            //This thread needs to be actively available, set up in a loop.

            while (true) {
                //OBS: select() is blocking by default!
                selector.select(); //This method selects a set of keys whose channels are ready for IO operations

                //If any events happen on the channels and we've registered for them, they'll be returned
                //when we invoke the selected keys method on the selector
                Set<SelectionKey> selectedKeys = selector.selectedKeys();

                //To handle any events that occur, we loop through the selection keys, which will tell us
                //what events have happened and which channels care about them.
                //To loop, we get an iterator for the keys, iterating while it still has data.
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key =iterator.next();
                    //We remove the key, as we are processing it
                    iterator.remove(); //The iterator make this easy, with it's remove method.

                    //The first event we will register interested, is when a client wants to establish a connection.
                    //We test it by calling the isAcceptable method on our key
                    if (key.isAcceptable()) {
                        //The event that a client is waiting to be accepted is now registered
                        SocketChannel clientChannel = serverChannel.accept();
                        System.out.println("Client Connected: " + clientChannel.getRemoteAddress());
                        //If we don't actually execute the accept method on our interested channel,
                        //this key will continue to be returned in the selected keys set

                        //Registering events to be executed
                        //We first ensure each client channel is non-blocking
                        clientChannel.configureBlocking(false);
                        //Event registered
                        clientChannel.register(selector, SelectionKey.OP_READ);

                        //React when a key gets selected:
                        //if the key is readable, then the client has made a request
                    } else if (key.isReadable()) {
                        echoData(key);

                    }
                }
            }
        } catch (IOException io){
            System.out.println(io.getMessage());
        }
    }

    //We need a method to process on client's request, and it's called whenever an event occurs
    //EVENT: Request from a client
    //A SelectionKey as a parameter is enough, since it contains the related channel as a attribute
    private static void echoData(SelectionKey key) throws IOException {

        //Setting a local variable for the client's socket channel, which is stored in the key
        //and we get it by using the accessor method
        SocketChannel clientChannel =(SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //Though the method is called read, it is actually writing request data from the channel to the buffer we pass
        //It will return th number of bytes we added to our buffer as a result.
        int bytesRead = clientChannel.read(buffer);

        //Processing the request, and echoing back as a response
        //we make sure that the buffer isn't empty, being greater than zero
        if (bytesRead > 0) {
            buffer.flip(); //From write to read

            //Reading the data as a Byte array, which is the same value of what's remaining
            //After flipping the buffer, the remaining will be de difference between limit and starting position
            byte[] data = new byte[buffer.remaining()];

            //We read info from the buffer into our byte array by calling the get method, and passing the byte array
            buffer.get(data);

            //We create a response string, that appends a string constructed from the byte array
            //using the system's default character set.
            String message = "Echo: " + new String(data);

            //To write the response to our client's channel, we use ByteBuffer's method wrap, passing a byte array
            clientChannel.write(ByteBuffer.wrap(message.getBytes()));
        } else if (bytesRead == -1) {

            System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
            //As the channel is gone, we want to clean up the selection keys that are associated with it
            key.cancel(); //keys removed
            clientChannel.close(); //channel closed
        }
    }
}
