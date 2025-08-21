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

    public boolean addSong(String title, double duration){

        var song = new Song (title, duration);

        if (findSong(song.getTitle()) == null){
            songs.add(song);
            return true;
        }
        System.out.println("Song already added in the album");
        return false;
    }

    public boolean addToPlaylist (int index, LinkedList<Song> album){

        for (Song song: songs){
            if(songs.get(index).equals(song)){
                songs.set( 1, song);
                return true;
            }
        }
        return false;
    }

    public Song findSong (String songTitle){

        for(Song song:songs){
            if (song.getTitle().equals(songTitle)){
                System.out.println("Song found! " + song.getTitle());
                return song;
            }
        }
        return null;
    }

    public String toString() {
        return name + " " + artist + " " + songs;
    }
}
