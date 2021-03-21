package part03;

import part01.Genre;
import part01.Tune;

public class TuneTest {

    public static void main(String[] args){
        testCase1();
        System.out.println();
        testCase2();

    }

    /**
     * Testing the object construction
     */
    public static void testCase1(){
        //test data
        String validTitle = "Highway To Hell";
        String validArtist = "AC/DC";
        int validDuration = 352;
        Genre validGenre = Genre.ROCK;

        Tune testTune;

        System.out.println("Test Case 1 - Testing Object Construction With Valid Data");
        System.out.println("=========================================================");
        try{
            testTune = new Tune(validTitle, validArtist, validDuration, validGenre);
            System.out.println("Object Construction Successful");
            System.out.println("Expected ID --> " + "1");
            System.out.println("Actual ID --> " + testTune.getId());
            System.out.println("Expected Title --> " + validTitle);
            System.out.println("Actual Title --> " + testTune.getTitle());
            System.out.println("Expected Artist --> " + validArtist);
            System.out.println("Actual Artist --> " + testTune.getArtist());
            System.out.println("Expected Duration --> " + validDuration);
            System.out.println("Actual Duration --> " + testTune.getDuration());
            System.out.println("Expected Play Count --> " + "0");
            System.out.println("Actual Play Count --> " + testTune.getPlayCount());
            System.out.println("Expected Title --> " + validTitle);
            System.out.println("Actual Title --> " + testTune.getTitle());
        }catch (Exception e){
            System.out.println("Exception: " + e);
            System.out.println("Error - This should not happen for valid data");
        }
        System.out.println("End Test Case 1");

    }

    /**
     * Testing object construction with invalid data
     */
    public static void testCase2(){
        Tune testTune = null;

        System.out.println("Test Case 2 - Testing Object Construction With Invalid Data");
        System.out.println("===========================================================");

        testTune = Utility.createTune(" ", "AC/DC", 555, Genre.CLASSICAL);
        if (testTune != null){
            System.out.println("Error: This should not be displayed");
            Utility.viewTune(testTune);
        }
        System.out.println();

        testTune = Utility.createTune("Slow Ride", null, 555, Genre.ROCK);
        if (testTune != null){
            System.out.println("Error: This should not be displayed");
            Utility.viewTune(testTune);
        }
        System.out.println();

        testTune = Utility.createTune("Here I Go Again", "Whitesnake", 0, Genre.ROCK);
        if (testTune != null){
            System.out.println("Error: This should not be displayed");
            Utility.viewTune(testTune);
        }
        System.out.println();

        testTune = Utility.createTune("Here I Go Again", "Whitesnake", 1, null);
        if (testTune != null){
            System.out.println("Error: This should not be displayed");
            Utility.viewTune(testTune);
        }
        System.out.println();

        testTune = Utility.createTune("Here I Go Again", "Whitesnake", -300, Genre.POP);
        if (testTune != null){
            System.out.println("Error: This should not be displayed");
            Utility.viewTune(testTune);
        }
        System.out.println();

        testTune = Utility.createTune(null, "Hozier", 350, Genre.POP);
        if (testTune != null){
            System.out.println("Error: This should not be displayed");
            Utility.viewTune(testTune);
        }
        System.out.println();

        testTune = Utility.createTune("Songbird", " ", 0, Genre.POP);
        if (testTune != null){
            System.out.println("Error: This should not be displayed");
            Utility.viewTune(testTune);
        }


    }

}
