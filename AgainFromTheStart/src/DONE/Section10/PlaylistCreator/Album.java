package DONE.Section10.PlaylistCreator;

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

        if (findSong(title) == null) {
            songs.add(new Song(title, duration));
            return true;
        }else return false;
    }

    private Song findSong (String title){

       for (var song : songs){
           if (song.getTitle() == title){

               return song;
           }
       }

       return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        int index = trackNumber - 1;
        // Verifica se o índice está entre 0 e songs.size()-1
        if (index >= 0 && index < songs.size()) {
            playList.add(songs.get(index));
            return true;
        }
        return false;
    }

    public boolean addToPlayList (String title, LinkedList<Song> playlist){

        for (var song : songs){
            if (song.getTitle() == title){
                playlist.add(song);
                return true;
            }

        }
        return false;
    }
}
