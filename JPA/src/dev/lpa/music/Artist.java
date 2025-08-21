package dev.lpa.music;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

//CREATING A JPA ENTITY: THE ANNOTATED POJO

//We use @ to do annotations or the JAP
@Entity // An entity usually has a relationship to a table, which is done after @Entity and before the class declaration
@Table(name = "artists") //Here we specify the name of the table in the music schema which is artists.
public class Artist {
    //Our artists table only has two columns, which will be set as fields in this class, to represent those columns

    @Id //Every db entity requires this on one field, which represents the primary key [PK]
    @Column(name = "artist_id") //primary key for our artists table
    private int artistId;

    @Column(name = "artist_name")
    private String artistName;

    //Keeping changes of parent and child in sync.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="artist_id")
    private List<Album> albums = new ArrayList<>();

    //The Constructor, Getters and Setters are necessary to manage this class

    //IMPORTANT: All JPA entities are required to have a no-args constructor.
    public Artist (){
    }

    public Artist(String artistName){
        this.artistName = artistName;
    }

    public Artist(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void addAlbum(String albumName) {
        albums.add(new Album(albumName));
    }

    public void removeDuplicates() {
        var set = new TreeSet<>(albums); //As we once learned from Sets = "A set contains no duplicates"
        albums.clear();
        albums.addAll(set); //So easy it feels like cheating
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", albums=" + albums +
                '}';
    }
}
