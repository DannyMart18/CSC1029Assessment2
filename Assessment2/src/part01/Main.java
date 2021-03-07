package part01;

import java.util.Scanner;

public class Main {
    //static scanner
    static Scanner input = new Scanner(System.in);
    //instance of MP3Player
    static MP3Player mp3 = new MP3Player();
    //boolean value set to true when the system starts
    static boolean running = true;


    public static void main(String[] args){
        //add initial objects to the mp3 player
        mp3.addTune("Highway To Hell", "AC/DC", 4, Genre.ROCK);
        mp3.addTune("Take On Me", "A-Ha", 4, Genre.POP);
        mp3.addTune("Fur Elise", "Beethoven", 5, Genre.CLASSICAL);
        mp3.addTune("Lies", "MK", 3, Genre.DANCE);
        mp3.addTune("So What", "Miles Davis", 4, Genre.JAZZ);
        mp3.addTune("She's a Hero", "Saint Sapphire", 3, Genre.OTHER);
        mp3.addTune("Back In Black", "AC/DC", 350, Genre.ROCK);




        //Menu options array
        String[] menuOptions = { "Select From Full List", "Select Tune By Artist", "Select Tune By Genre",
                "Add New Tune", "Display Top 10", "Switch Off", "Switch On", "Exit" };

        //instance of the menu class
        Menu appMenu = new Menu("QUB Music Management", menuOptions);
        int option = appMenu.getUserChoice();
        while (option != menuOptions.length) {
            processOption(option);
            System.out.println();
            option = appMenu.getUserChoice();
        }
        System.out.println("Goodbye!");
        input.close();
    }

    //calls the methods as listed in menu options
    private static void processOption(int option) {
        switch (option) {
            case 1 -> selectFromFullList();
            case 2 -> selectTuneByArtist();
            case 3 -> selectTuneByGenre();
            case 4 -> addNewTune();
            case 5 -> System.out.println("Not yet Implemented yet");
            case 6 -> switchOff();
            case 7 -> switchOn();
            default -> System.out.println("Invalid Option: " + option + "\n");
        }
    }

    /*
    *Allows the user to view all songs available in the system and play a song. Checks if system is switched
    * on or off
    * */
    private static void selectFromFullList(){
        if(running) {
            String[] songs = mp3.getTuneInfo();
            int len = songs.length;

            String[] display = new String[len];
            int index = 0;
            for (String song : songs) {
                String artist = song.toString();
                String[] tunes = artist.split(",");
                display[index] = tunes[1] + " By" + tunes[2];
                index++;
            }

            removeDuplicateElements(display, len);
            Menu songMenu = new Menu("Select a Song",display);
            int choice = songMenu.getUserChoice();
            System.out.println();
            System.out.println(mp3.play(choice-1));

        }else{
            System.out.println("System is switched off");
        }
    }
    /*
    * Allows the user to select a song by viewing the artist first
    * */
    private static void selectTuneByArtist(){
        if(running){
            String[] songs = mp3.getTuneInfo();
            int len = songs.length;

            //Splits the getTuneInfo and isolates the artists and prints them
            String[] artists = new String[len];
            int index = 0;
            for (int i = 0; i < len; i++){
                String artist = songs[i].toString();
                String[] tunes = artist.split(",");
                artists[index] = tunes[2];
                index++;
            }

            bubbleSort(artists);
            len = removeDuplicateElements(artists, len);

            int count = 1;
            for(int i = 0; i <len; i++){
                System.out.println(count++ + ". " + artists[i]);
            }
            System.out.println();

            System.out.println("Enter The Artist You Would Like To View");
            Scanner artistChoice = new Scanner(System.in);
            String choice = artistChoice.nextLine();


            Menu artistMenu = new Menu("Songs", mp3.getTuneInfo(choice));
            int songChoice = artistMenu.getUserChoice();

            int song =  retrieveTuneID(mp3.getTuneInfo(choice),songChoice );


            System.out.println(mp3.play(song-1));
            System.out.println();
        }else{
            System.out.println("System is switched off");
        }
    }

    /**
     *
     */
    private static void selectTuneByGenre() {
        if (running) {
            String[] songs = mp3.getTuneInfo();
            int len = songs.length;

            //Splits the getTuneInfo and isolates the artists and prints them
            String[] genres = new String[len];
            int index = 0;
            for (int i = 0; i < len; i++) {
                String artist = songs[i].toString();
                String[] tunes = artist.split(",");
                genres[index] = tunes[4];
                index++;
            }


            bubbleSort(genres);
            len = removeDuplicateElements(genres, len);

            int count = 1;
            for (int i = 0; i < len; i++) {
                System.out.println(count + ". " + genres[i]);
                count++;
            }
            System.out.println(count++ + ". " + " Unknown");
            System.out.println();

            Genre genre;
            int num = 0;
            boolean ok = false;
            do {
                System.out.println("Enter The Genre You Would Like To View");
                try {
                    num = input.nextInt();
                    if (num > 0 && num <= genres.length) {
                        ok = true;
                    } else {
                        System.out.println("That option isn't available! \nEnter a value between 1 and " + genres.length);
                    }
                } catch (Exception ex) {
                    System.out.println("Error invalid input.");
                    input.nextLine();
                }
            }while (!ok);
            if (num == 1) {
                genre = Genre.CLASSICAL;
            } else if (num == 2) {
                genre = Genre.POP;
            } else if (num == 3) {
                genre = Genre.ROCK;
            } else if (num == 4) {
                genre = Genre.JAZZ;
            } else if (num == 5) {
                genre = Genre.DANCE;
            } else {
                genre = Genre.OTHER;
            }
            Menu genreMenu = new Menu("Songs", mp3.getTuneInfo(genre));
            int songChoice = genreMenu.getUserChoice();

            int song =  retrieveTuneID(mp3.getTuneInfo(genre),songChoice );


            System.out.println(mp3.play(song-1));
            System.out.println();

        } else {
            System.out.println("System is switched off");
        }
    }

    /**
     * displays the top 9 songs
     */
    private static void displayTopTen(){





     }

    /**
     * retrieves the tune ID and allows a tune to be played by index as opposed to ID
     * @param data array of songs
     * @param choice the user choice
     * @return the tune ID
     */
     private static int retrieveTuneID(String[] data, int choice){
        String[] ID = data[choice-1].split(", ");
        String tuneID = ID[0];
        int tuneId = Integer.parseInt(tuneID);
        return tuneId;
     }


    //adds a new tune to the system
    private static void addNewTune() {
        if (running) {
            Scanner tune = new Scanner(System.in);
            Genre genre;
            System.out.println("Enter the song title");
            String title = tune.nextLine();
            System.out.println("Enter the artist");
            String artist = tune.nextLine();
            System.out.println("Enter the duration");
            int duration = tune.nextInt();
            System.out.println("Enter the Genre:\n1. Rock\n2. Pop\n3. Dance\n4. Jazz\n5. Classical\n6. Other");
            int num = tune.nextInt();
            if (num == 1) {
                    genre = Genre.ROCK;
                } else if (num == 2) {
                    genre = Genre.POP;
                } else if (num == 3) {
                    genre = Genre.DANCE;
                } else if (num == 4) {
                    genre = Genre.JAZZ;
                } else if (num == 5) {
                    genre = Genre.CLASSICAL;
                } else {
                    genre = Genre.OTHER;
                }

                if (mp3.addTune(title, artist, duration, genre)) {
                    System.out.println("Song has been added");
                } else {
                    System.out.println("Invalid information provided - Try Again");
                }
        } else {
            System.out.println("System is switched off");

        }
    }
    //switches the system off
    private static void switchOff(){
        if(mp3.switchOn()){
            System.out.println("System has been shut down");
            running = false;
        }else{
            System.out.println("System is already off");
        }
    }
    //switches the system on
    private static void switchOn(){
        if(mp3.switchOff()){
            System.out.println("The system has been switched on");
            running = true;
        }else{
            System.out.println("System is already on");
        }
    }

    /**
     * removes duplicate elements from a passed in array and length
     *
     */
    private static int removeDuplicateElements(String[] data, int n){
        if (n==0 || n==1){
            return n;
        }

        String[] temp = new String[n];
        int j = 0;
        for (int i=0; i<n-1; i++){
            if (!data[i].equalsIgnoreCase(data[i+1])){
                temp[j++] = data[i];
            }
        }

        // Changing original array
        for (int i=0; i<j; i++){
            data[i] = temp[i];
        }
        return j;
    }

    public static void bubbleSort(String[] data) {
        boolean sorted = false;
        String temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < data.length - 1; i++) {
                if (data[i].compareTo(data[i+1]) > 0){
                    temp = data[i];
                    data[i] = data[i+1];
                    data[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
}