package part01;

import java.util.ArrayList;

public class MP3Player implements iPlayer {
    private ArrayList<Tune> soundData = new ArrayList<Tune>();

    public MP3Player(){

    }


    public String[] getTuneInfo(){
        if(!soundData.isEmpty()){
                String res[] = new String[soundData.size()];
                for(int i =0; i < res.length; i++){
                    res[i]= soundData.get(i).getId() + ", " +
                            soundData.get(i).getTitle() + ", " + soundData.get(i).getArtist() + ", " + soundData.get(i).getDuration() + ", " +
                    soundData.get(i).getStyle();
                }
                return res;
            }
        else{
            return null;
        }

        }

    //Genre
    public String[] getTuneInfo(Genre gen) {
        if (!soundData.isEmpty()) {
            ArrayList<Tune> sounds = new ArrayList<Tune>();
            sounds = findGenre(gen);

            String songs[] = new String[sounds.size()];

            int index = 0;
            for (Tune tune : sounds) {
                songs[index] = String.valueOf(tune);
                index++;
            }
            return songs;
        }else{
            return null;
        }
    }



    // String
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


    public String play(int tuneID){
        String play = "";
        play += soundData.get(tuneID).play();
        return play;
    }

    public boolean addTune(String title, String artist, int duration, Genre genre){
        if(title!= null && artist != null && duration > 0 && genre != null){
            Tune t = new Tune(title, artist, duration, genre);
            soundData.add(t);
            return true;
        }

        return false;
    }

    public boolean switchOff(){
        return true;
    }

    public boolean switchOn(){
        return true;
    }
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
