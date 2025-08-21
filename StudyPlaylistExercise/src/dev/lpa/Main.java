package dev.lpa;

public class Main {
    public static void main(String[] args) {

        var test = new Album("No rest for the wicked", "Ozzy Osbourne");

        test.addSong("Miracle Man", 3.44);
        test.addSong("Crazy Babies", 4.15);
        test.addSong("Bloodbath in Paradise", 5.13);
        test.addSong("Breakin' All the Rules", 5.15);
        test.addSong("Hero", 4.49);

        test.findSong("ur mom");

        test.addSong("Hero", 4.49);


        System.out.println(test);
    }
}
