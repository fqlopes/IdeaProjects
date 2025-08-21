package DONE.Section10.PlaylistCreator;

public class Song {

    private String title;
    private double duration;

    public Song (String title, double duration){
        this.title = title;
        this.duration = duration;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public String toString(){
        System.out.println("Title: Duration");
        return "%s: %f".formatted(this.title, this.duration);
    }
}
