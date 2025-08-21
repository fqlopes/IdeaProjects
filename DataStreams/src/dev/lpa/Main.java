package dev.lpa;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//When we serialize an instance of a class that is implementing Serializable,
//all its fields will be serialized, including complex types such as Lists.
//Hooks: readObject and writeObject, and don't need to be overridden,
//but when implemented, the serialization process will use our explicit versions.
//We have to stick to the signatures of a class's methods, as they're identified,
//in the Serialization documentation
class Player implements Serializable {

    //Explicitly declaring the serialVersionUID
    @Serial
    private final static long serialVersionUID = 1L;

    private final static int version = 2;

    private String name;
    private long topScore;

    //Compatible Change: Adding more fields
    //Won't break, but won't be populated either, if trying to force an overwrite
    private long bigScore;

    private final transient long accountId;

    private List<String> collectedWeapons = new LinkedList<>();

    public Player(long accountId, String name, int topScore, List<String> collectedWeapons) {
        this.accountId = accountId;
        this.name = name;
        this.topScore = topScore;
        this.collectedWeapons = collectedWeapons;
    }

    @Override
    public String toString() {
        return "Player{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", topScore=" + topScore +
                ", collectedWeapons=" + collectedWeapons +
                '}';
    }

    //Implementation of readObject, and is a special method whose signature doesn't change
    //Its good practice to add the annotation, to inform this method is used in serialization
    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {

        //We the method is created, we want to the default serialization process first,
        //by invoking the method defaultReadObject in our ObjectInputStream argument
//        stream.defaultReadObject();

        //One thing we can do, is make changes to the object that got de-serialized
//        bigScore = (bigScore == 0) ? 1_000_000_000L : bigScore;

        var serializedVer = stream.readInt();
        collectedWeapons = (List<String>) stream.readObject();
        name = stream.readUTF();
        topScore = (serializedVer == 1) ? stream.readInt() : stream.readLong();
    }

    //When creating a customized writeObject method, we can't simply
    //delegate to the default readObject method.
    //If not changed, writes will be out of sync, and the code will fail
    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {

        System.out.println("---> Customized Writing");
        stream.writeInt(version);
        stream.writeObject(collectedWeapons);
        stream.writeUTF(name);
        stream.writeLong(topScore);
    }
}


public class Main {
    public static void main(String[] args) {

        //Creating a path variable to work with our method
//        Path dataFile = Path.of("data.dat");
//        writeData(dataFile);
//        readData(dataFile);

        Player tim = new Player(555, "Tim", 100_000_010, List.of("knife", "machete", "pistol"));
        System.out.println(tim);

        Path timFile = Path.of("tim.dat");
//        writeObject(timFile, tim);
        Player reconstructedTim = readObject(timFile);
        System.out.println(reconstructedTim);

        Player joe = new Player(556, "Joe", 75, List.of("crossbow", "rifle", "pistol"));
        Path joeFile = Path.of("joe.dat");
        writeObject(joeFile, joe);
        Player reconstitutedJoe = readObject(joeFile);
        System.out.println(joe);
        System.out.println(reconstitutedJoe);

    }

    //We start by creating a method to write data to a path
    private static void writeData(Path dataFile){

        //We then create an instance of DataOutputStream, inside a try-with-resources
        //this type will wrap a buffered version of it, which in turn
        //will also wrap a FileOutputStream, which takes our path instance
        //We didn't really to wrap the BufferedOutputStream,
        //however this is the standard way of doing it.
        //Most files will benefit from being wrapped in BufferedOutputStream
        try (DataOutputStream dataStream =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile.toFile())))){

            //Creating local variables for each primitive type + String
            int myInt = 17;
            long myLong = 100_000_000_000_000L;
            boolean myBoolean = true;
            char myChar = 'Z';
            float myFloat = 77.7f;
            double myDouble = 98.6;
            String myString = "Hello World";

            //We will create a long variable to help track how many bytes each operation is writing
            long position = 0;

            //Writing using our dataStream variable
            dataStream.writeInt(myInt);

            //Posting what is being written while calculating how many bytes were written
            System.out.println("writeInt writes : " + (dataStream.size() - position));

            //Updating position, to the current size
            position = dataStream.size();

            //We will use these 3 statements to set up other
            dataStream.writeLong(myLong);
            System.out.println("writeLong writes : " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeBoolean(myBoolean);
            System.out.println("myBoolean writes : " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeChar(myChar);
            System.out.println("myChar writes : " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeFloat(myFloat);
            System.out.println("myFloat writes : " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeDouble(myDouble);
            System.out.println("myDouble writes : " + (dataStream.size() - position));
            position = dataStream.size();

            dataStream.writeUTF(myString);
            //Though "Hello World" only have 11 characters,
            //writeUTF uses the first 2 bytes to record the number of characters written
            System.out.println("writeUTF writes : " + (dataStream.size() - position));
            position = dataStream.size();


        } catch (IOException e){
            System.out.println("lmao nothing to see here");
        }
    }
    //Now that we got data in a binary file, we can read using DataInputStream
    //We will also build a custom method for reading
    private static void readData (Path dataFile){

        //We start with a try-with-resources, where we declare our DataInputStream
        //Instead of passing a BufferedOutputStream, we'll use the static method in Files
        //Files.newInputStream, which doesn't need to be nested in anything
        try (DataInputStream dataStream =
                new DataInputStream(Files.newInputStream(dataFile))){

            //Reading the data in, from a file:
            //For a DataInputStream, we need to know the data types we're reading in,
            //and the same order the types were output in the file.

            //Printing each data type
            System.out.println("myInt = " + dataStream.readInt());
            System.out.println("myLong = " + dataStream.readLong());
            System.out.println("myBoolean = " + dataStream.readBoolean());
            System.out.println("myChar = " + dataStream.readChar());
            System.out.println("myFloat = " + dataStream.readFloat());
            System.out.println("myDouble = " + dataStream.readDouble());
            System.out.println("myString = " + dataStream.readUTF()); //if written with UTF, its read with UTF

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static void writeObject(Path dataFile, Player player){
        try (ObjectOutputStream objStream = new ObjectOutputStream(Files.newOutputStream(dataFile))){
            objStream.writeObject(player);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static Player readObject(Path dataFile) {
        try(ObjectInputStream objStream = new ObjectInputStream(Files.newInputStream(dataFile))){
            return (Player) objStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
