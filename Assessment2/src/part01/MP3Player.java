package part01;

import java.util.ArrayList;

public class MP3Player implements iPlayer {
    private ArrayList<Tune> soundData = new ArrayList<Tune>();

    public MP3Player(){

    }


    /**
     *
     * @return
     */
    public String[] getTuneInfo(){
        if(!soundData.isEmpty()){
                String res[] = new String[soundData.size()];
                for(int i =0; i < res.length; i++){
                    res[i]= soundData.get(i).toString();
                }

                return res;
            }
        else{
            return null;
        }

        }

    /**
     *
     * @param gen
     * @return
     */
    public String[] getTuneInfo(Genre gen) {
        ArrayList<Tune> sounds = new ArrayList<Tune>();
        if (!soundData.isEmpty()) {
            sounds = findGenre(gen);

            String songs[] = new String[sounds.size()];

            int index = 0;
            for (Tune tune : sounds) {
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
     * @param artist
     * @return
     */
    public String[] getTuneInfo(String artist){
        ArrayList<Tune>sounds = new ArrayList<Tune>();
        if(findArtist(artist) >= 0){
            sounds = findArtists(artist);

            String songs[] = new String[sounds.size()];

            int index =0;
            for(Tune tune: sounds){
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
     * @return
     */
    public String play(int tuneID){

        String play = "";
        play += soundData.get(tuneID).play();
        return play;
    }

    /**
     *
     * @param title
     * @param artist
     * @param duration
     * @param genre
     * @return
     */
    public boolean addTune(String title, String artist, int duration, Genre genre){
        if(title!= null && artist != null && duration > 0 && genre != null){
            try {
                Tune t = new Tune(title, artist, duration, genre);
                soundData.add(t);
            }catch(Exception ex){
                System.out.println(ex.toString());
            }

            return true;
        }

        return false;
    }

    /**
     *
     * @return
     */
    public boolean switchOff(){
        return true;
    }

    /**
     *
     * @return
     */
    public boolean switchOn(){
        return true;
    }


    /**
     *
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
     *
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
