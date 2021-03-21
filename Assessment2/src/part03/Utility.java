package part03;

import part01.Genre;
import part01.Tune;

public class Utility {

    /**
     *Utility class to reduce repetition of code for testing Tune object construction
     */

    public static Tune createTune (String title, String artist, int duration, Genre gen){
        Tune test = null;
        try{
            test = new Tune(title, artist, duration, gen);
            System.out.println("Song added");
        }catch(Exception ex){
            System.out.println("Could not add the song " + ex);
        }
        return test;
    }

    public static void viewTune(Tune test){
        if(test != null){
            System.out.println("ID: " + test.getId());
            System.out.println("Title:  " + test.getTitle());
            System.out.println("Artist: " + test.getArtist());
            System.out.println("Duration: " + test.getDuration());
            System.out.println("Genre: " + test.getStyle());
            System.out.println("Play Count: " + test.getPlayCount());

        }
    }

}
