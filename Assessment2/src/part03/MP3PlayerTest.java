package part03;

import part01.Genre;
import part01.MP3Player;

public class MP3PlayerTest {
    /**
     *This will test methods from the MP3Player class
     */
    static MP3Player mp3 = new MP3Player();

    public static void main(String[] args){
        testCase1();
        System.out.println();
        testCase2();
        System.out.println();
        testCase3();
        System.out.println();
        testCase4();
        System.out.println();
        testCase5();
        System.out.println();
        testCase6();
        System.out.println();
        testCase7();
    }


    /**
     * testing getTuneInfo
     */
    private static void testCase1(){
        mp3.addTune("Dreams", "Fleetwood Mac", 500, Genre.POP);
        mp3.addTune("Songbird", "Fleetwood Mac", 500, Genre.POP);
        mp3.addTune("Thunderstruck", "AC/DC", 350, Genre.ROCK);
        mp3.addTune("Take On Me", "A-Ha", 500, Genre.POP);
        mp3.addTune("Fur Elise", "Beethoven", 240, Genre.CLASSICAL);
        mp3.addTune("Lies", "MK", 120, Genre.DANCE);
        mp3.addTune("So What", "Miles Davis", 400, Genre.JAZZ);
        mp3.addTune("She's a Hero", "Saint Sapphire", 300, Genre.OTHER);
        String[] data = mp3.getTuneInfo();
        System.out.println("Test Case 1 - Testing getTuneInfo()");
        System.out.println("===================================");
        for(int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
    }

    /**
     * testing getTuneInfo(artist)
     */
    public static void testCase2(){
        mp3.addTune("Fur Elise", "Beethoven", 500, Genre.CLASSICAL);
        mp3.addTune("5th Caprice", "Beethoven", 500, Genre.CLASSICAL);
        String data[] = mp3.getTuneInfo();
        String[] testArtist = mp3.getTuneInfo("Beethoven");
        String[] testArtist2 = mp3.getTuneInfo("Fleetwood Mac");
        System.out.println("Test Case 2 - Testing getTuneInfo(String artist)");
        System.out.println("================================================");
        System.out.println("All songs in the array");
        System.out.println("++++++++++++++++++++++");
        for(int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
        System.out.println();
        System.out.println("Songs displayed when BEETHOVEN is chosen");
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        for(int i = 0; i < testArtist.length; i++){
            System.out.println(testArtist[i]);
        }
        System.out.println();
        System.out.println("Songs displayed when (FLEETWOOD MAC) is chosen");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        for(int i = 0; i < testArtist2.length; i++){
            System.out.println(testArtist2[i]);
        }

    }

    /**
     * testing getTuneInfo(genre)
     */
    public static void testCase3(){
        mp3.addTune("Whiplash", "Hank Levy", 500, Genre.JAZZ);
        mp3.addTune("Practicing", "Justin Hurwitz", 500, Genre.JAZZ);
        String data[] = mp3.getTuneInfo();
        String[] testGenre = mp3.getTuneInfo(Genre.JAZZ);
        String[] testGenre2 = mp3.getTuneInfo(Genre.ROCK);
        System.out.println("Test Case 3 - Testing getTuneInfo(Genre gen)");
        System.out.println("================================================");

        System.out.println("All songs in the array");
        System.out.println("++++++++++++++++++++++");
        for(int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
        System.out.println();
        System.out.println("Songs displayed when JAZZ is chosen");
        System.out.println("+++++++++++++++++++++++++++++++++++");
        for(int i = 0; i < testGenre.length; i++){
            System.out.println(testGenre[i]);
        }

        System.out.println();
        System.out.println("Songs displayed when (ROCK) is chosen");
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        for(int i = 0; i < testGenre2.length; i++){
            System.out.println(testGenre2[i]);
        }
    }

    /**
     * testing the play method with valid data
     */
    public static void testCase4(){
        int validID = 0;
        System.out.println("Test Case 4 - Testing play()");
        System.out.println("============================");
        System.out.println("Expected output: " + "Now Playing... Dreams By Fleetwood Mac");
        System.out.println("Actual output: " + mp3.play(validID));
    }

    /**
     * testing the play method with invalid data
     */
    public static void testCase5(){
        int invalidID1 = -5;
        int invalidID2 = 15;
        System.out.println("Test Case 5 - Testing play()");
        System.out.println("============================");

        System.out.println("Testing a negative number " + invalidID1);
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        System.out.println("Expected output: " + "Invalid Tune ID");
        System.out.println("Actual output: " + mp3.play(invalidID1));
        System.out.println();

        System.out.println("Testing an ID greater soundData.size() " + invalidID2);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Expected output: " + "Invalid Tune ID");
        System.out.println("Actual output: " + mp3.play(invalidID2));


    }

    /**
     * testing the addTune method with valid data
     */
    public static void testCase6(){
        String validTitle = "Highway To Hell";
        String validArtist = "AC/DC";
        int validDuration = 352;
        Genre validGenre = Genre.ROCK;
        System.out.println("Test Case 6 - Testing addTune()");
        System.out.println("===============================");
        System.out.println("Expected boolean: " + true);
        System.out.println("Actual boolean: " +mp3.addTune(validTitle, validArtist, validDuration, validGenre));
        System.out.println("The following print statement will show the object has been created");
        System.out.println(mp3.getTuneInfo()[12-1]);

    }

    /**
     * testing addTune with valid data but a duplicate object
     */
    public static void testCase7(){
        String validTitle = "Highway To Hell";
        String validArtist = "AC/DC";
        int validDuration = 352;
        Genre validGenre = Genre.ROCK;
        System.out.println("Test Case 7 - Testing addTune()");
        System.out.println("===============================");
        System.out.println("Expected boolean: " + false);
        System.out.println("Actual boolean: " +mp3.addTune(validTitle, validArtist, validDuration, validGenre));
        

    }




}
