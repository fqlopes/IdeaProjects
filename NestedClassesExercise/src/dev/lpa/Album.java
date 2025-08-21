package dev.lpa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {
    private String name;
    private String artist;
    private SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public boolean addSong(String title, double duration ) {
        if (songs.findSong(title) == null){
            songs.add(new Song (title, duration));
            return true;
        }
        return false;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playlist) {
        if (songs.findSong(trackNumber) != null){
            playlist.add(songs.songs.get(trackNumber-1));
            return true;
        }
        System.out.println("This album does not have a track " + trackNumber);
        return false;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playlist) {
        Song song = songs.findSong(title);
        if (song == null) {
            System.out.println("The song " + title + " is not in this album");
            return false;
        }
        playlist.add(song);
        return true;
    }


    public static class SongList {
        private ArrayList<Song> songs;

        private SongList() {
            songs = new ArrayList<Song>();
        }

        private boolean add(Song songToAdd){

            songs.add(songToAdd);
            return true;
        }

        private Song findSong(String title) {
            for (var song : songs){
                if (song.getTitle().equals(song.getTitle()));
                return song;
            }
            return null;
        }

        private Song findSong(int trackNumber) {

            int index = songs.size() -1;
            for (int i = 0; i < songs.size(); i ++){
                var song = songs.get(i);
                if(index <= songs.size() && song.equals(songs.get(index))){
                    return songs.get(index);
                }
            }
            return null;
        }
    }
}