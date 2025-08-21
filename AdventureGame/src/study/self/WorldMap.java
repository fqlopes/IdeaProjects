package study.self;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WorldMap {


    private record Exit (Direction direction, String connectedRoom){}
    //outer hashmap = location and it's exits
    //inner hashmap = exits
    private HashMap<String, HashMap<Direction, String>> worldMap;
    private enum Direction  {
        N, S, E, W;

        @Override
        public String toString (){
            return name().toString().toUpperCase();
        }
    }
    private String currentRoom;
    private Scanner scanner;

    public WorldMap (){

        scanner = new Scanner(System.in);
        currentRoom = "Entrance";
        createWorld();
    }

    private void createWorld () {

        worldMap = new HashMap<>();
        addRoom("Entrance", new Exit(Direction.N, "Cavern"),
                new Exit(Direction.S, "Beach"),
                new Exit(Direction.E, "Forest"),
                new Exit(Direction.W, "Village"));

        addRoom("Cavern", new Exit(Direction.S, "Entrance"));
        addRoom("Beach", new Exit(Direction.N, "Entrance"));
        addRoom("Forest", new Exit(Direction.W, "Entrance" ));
        addRoom("Village", new Exit(Direction.E, "Entrance"));


    }

    private void addRoom (String roomName, Exit... exits){
        HashMap <Direction, String> connections = new HashMap<>();

        for (Exit exit : exits){
            connections.put(exit.direction, exit.connectedRoom );
        }
        worldMap.put(roomName, connections);
    }

    private void move(Direction direction){
        HashMap<Direction, String> exits = worldMap.get(direction);
    }

    public void play(){
        while (true){
            String input = scanner.nextLine().toUpperCase();
            Direction direction = Direction.valueOf(input);
            move(direction);
        }
    }

}
