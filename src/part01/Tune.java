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

    public Tune(String title, String artist, int duration, Genre style) throws Exception{
        this.id = useNextID();
        if(title == null){
            throw new Exception("Title must not be null");
        }else{
            this.title = title;
        }if(artist == null){
            throw new Exception("Artist must not be null");
        }else{
            this.artist = artist;
        }
        if(duration <= 0){
            throw new Exception("Duration must be greater than zero");
        }else{
            this.duration = duration;
        }
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

    public void setPlayCount(int count) {
        playCount += count;
    }


    public Genre getStyle() {
        return style;
    }

    public void setStyle(Genre style) {
        this.style = style;
    }

    public String toString(){
        String res = "";
        res += this.getId() + ", ";
        res += this.getTitle() +", ";
        res += this.getArtist()+ ", ";
        res += this.getDuration() + ", ";
        res += this.getStyle() +",";
        res += this.getPlayCount();
        return res;
    }

    public String play(){
        playCount++;
        String message = "Now Playing... ";
        message += this.getTitle() + " By " + this.getArtist();
        return message;


    }
}
