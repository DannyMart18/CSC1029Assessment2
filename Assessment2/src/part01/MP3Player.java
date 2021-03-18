package part01;

import java.util.ArrayList;

public class MP3Player implements iPlayer {
    private ArrayList<Tune> soundData = new ArrayList<Tune>();

    public MP3Player(){

    }
    /**
     *
     * @return songs array
     */
    public String[] getTuneInfo() {
        ArrayList<Tune> data = new ArrayList<Tune>();
        if (!soundData.isEmpty()) {
            data = soundData;
            String[] songs = new String[soundData.size()];
            int index = 0;
            for (Tune tune : data) {
                songs[index] = String.valueOf(tune);
                index++;
            }
            return songs;

        } else {
            return null;
        }
    }
    /**
     *
     * @param gen which the user chooses
     * @return an array with all the songs from the chosen genre
     */
    public String[] getTuneInfo(Genre gen) {
        ArrayList<Tune> data = new ArrayList<Tune>();
        if (!soundData.isEmpty()) {
            data = findGenre(gen);

            String songs[] = new String[data.size()];

            int index = 0;
            for (Tune tune : data) {
                songs[index] = String.valueOf(tune);
                index++;
            }
            return songs;
        } else {
            return null;
        }
    }

    /**
     *
     * @param artist which the user chooses
     * @return a String array with all the songs from the artist chosen
     */
    public String[] getTuneInfo(String artist){
        ArrayList<Tune>data = new ArrayList<Tune>();
        if(findArtist(artist) >= 0){
            data = findArtists(artist);

            String songs[] = new String[data.size()];

            int index =0;
            for(Tune tune: data){
                songs[index]= String.valueOf(tune);
                index ++;
            }
            return songs;
        }else{
            return null;
        }
    }


    /**
     *
     * @param tuneID
     * @return A string to say the song is playing
     */
    public String play(int tuneID){
        String play = "";
        if (tuneID < 0) {
            play += "Invalid Tune ID";
            return play;
        }else {
            play += soundData.get(tuneID).play();
            return play;
        }
    }

    /**
     *adds a new tune to soundData
     * @param title
     * @param artist
     * @param duration
     * @param genre
     * @return
     */
    public boolean addTune(String title, String artist, int duration, Genre genre){
                if(title!= null && artist != null && duration > 0 && genre != null){
                    if(findDuplicates(title) >= 0 ){
                        return false;
                    }else {
                        try {
                            Tune t = new Tune(title, artist, duration, genre);
                            soundData.add(t);
                        } catch (Exception ex) {
                            throw new IllegalArgumentException(ex);
                        }

                        return true;
                    }
            }
            return false;
        }

    /**
     *
     * @return true
     */
    public boolean switchOff(){
        return true;
    }

    /**
     *
     * @return true
     */
    public boolean switchOn(){
        return true;
    }

    private int findDuplicates(String title){
        for (int i = 0; i< soundData.size(); i++){
            Tune tune = this.soundData.get(i);
            if(tune.getTitle().equalsIgnoreCase(title)){
                return i;
            }
        }
        return -1;
    }



    /**
     *Finds the genre in soundData
     * @param gen
     * @return
     */
    private ArrayList<Tune> findGenre(Genre gen){
        ArrayList<Tune>genre = new ArrayList<Tune>();
        for (int i =0; i < this.soundData.size(); i++){
            Tune tune = this.soundData.get(i);
            if(tune.getStyle() == gen){
                genre.add(tune);
            }
        }
        return genre;

    }

    /**
     *
     * @param gen
     * @return
     */
    private int Genre(Genre gen){
        for (int i = 0; i< soundData.size(); i++){
            Tune tune = this.soundData.get(i);
            if(tune.getStyle() == (gen)){
                return i;
            }
        }
        return -1;
    }



    /**
     *Finds the artist in soundData
     * @param artist
     * @return
     */
    private ArrayList<Tune> findArtists(String artist){
        ArrayList<Tune>sounds = new ArrayList<Tune>();
        for (int i =0; i < this.soundData.size(); i++){
            Tune tune = this.soundData.get(i);
            if(tune.getArtist().equalsIgnoreCase(artist)){
                sounds.add(tune);
            }
        }
        return sounds;

    }

    /**
     *
     * @param artist
     * @return
     */
    private int findArtist(String artist){
        for (int i = 0; i< soundData.size(); i++){
            Tune tune = this.soundData.get(i);
            if(tune.getArtist().equalsIgnoreCase(artist)){
                return i;
            }
        }
        return -1;
    }














}
