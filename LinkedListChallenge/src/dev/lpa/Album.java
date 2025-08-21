package dev.lpa;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album (String name, String artist){
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong (String title, double duration){
        var newSong = new Song (title, duration);
        if (findSong(title) == null) {
            songs.add(newSong);
            return true;
        }
        return false;
    }

    public Song findSong (String title){
        for (int i = 0; i < songs.size(); i++){
            if (songs.get(i).getTitle().equals(title)){
                return songs.get(i);
            }
        }
        return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playlist) {
        if (trackNumber >= 1) {
            playlist.add(songs.get(trackNumber - 1)); // add to the first of the list
            return true;
        }
        return false;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playlist) {
        Song song = findSong(title);
        if (song == null) {
            return false;
        }
        playlist.add(song);
        return true;
    }
}