package part01;

public class Tune implements iTune{
    //instance variables
    private int id;
    private static int nextId = 1;
    private String title;
    private String artist;
    private int duration;
    private int playCount;
    private Genre style;

    public Tune(String title, String artist, int duration, Genre style){
        this.id = useNextID();
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.style = style;
        this.playCount = 0;

    }

    public int useNextID(){
        return nextId++;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayCount() {
        return playCount;
    }


    public Genre getStyle() {
        return style;
    }

    public void setStyle(Genre style) {
        this.style = style;
    }

    public String toString(){
        String res = "";
        res += "ID: " + this.getId() + ", ";
        res += "Title: " + this.getTitle() +", ";
        res += "Artist: " + this.getArtist()+ ", ";
        res += "Duration: " + this.getDuration() + " seconds, ";
        res += "Genre: " + this.getStyle();
        return res;
    }

    public String play(){
        playCount++;
        String message = "Now Playing... ";
        message += this.getTitle() + " By " + this.getArtist();
        return message;


    }
}
