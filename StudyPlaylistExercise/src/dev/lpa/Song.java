package dev.lpa;

public class Song {

    private String title;
    private double duration;

    public Song (String title, double duration){
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        System.out.println("-".repeat(30));
        return title + " " + "duration " + duration;

    }
}
