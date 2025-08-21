package dev.lpa.music;

import jakarta.persistence.*;

@Entity
@Table(name = "albums")
//Some albums are duplicated, and to deal with them, we'll add a method to the artist entity to remove duplicates.
//To do this, the Album class must implement Comparable.
public class Album implements Comparable<Album>{

    @Id

    //@GeneratedValue, which requires a strategy to be specified
    //This informs the provider that the database is going to be assigning the id value
    //This annotation is required by this JPA Provider, to ensure that the parentId, the artistId, the foreign key
    //is properly saved to the child, during the insertion of a new record
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private int albumId;

    @Column(name = "album_name")
    private String albumName;

    public Album(){
    }

    public Album(String albumName){
        this.albumName = albumName;
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Album o) {
        return this.getAlbumName().compareTo(o.getAlbumName());
    }
}
