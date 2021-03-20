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
        //adds initial tune objects to the system
        addTunes();

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
            case 5 -> displayTopTen();
            case 6 -> switchOff();
            case 7 -> switchOn();
            default -> System.out.println("Invalid Option: " + option + "\n");
        }
    }

    //adds songs to mp3 player on program start up
    private static void addTunes(){
        //add initial objects to the mp3 player
        mp3.addTune("Highway To Hell", "AC/DC", 350, Genre.ROCK);
        mp3.addTune("Take On Me", "A-Ha", 500, Genre.POP);
        mp3.addTune("Fur Elise", "Beethoven", 240, Genre.CLASSICAL);
        mp3.addTune("Lies", "MK", 120, Genre.DANCE);
        mp3.addTune("So What", "Miles Davis", 400, Genre.JAZZ);
        mp3.addTune("She's a Hero", "Saint Sapphire", 300, Genre.OTHER);
        mp3.addTune("Back In Black", "AC/DC", 350, Genre.ROCK);
        mp3.addTune("Caroline", "Status Quo", 350, Genre.ROCK);
        mp3.addTune("Symphony", "Mozart", 350, Genre.CLASSICAL);
        mp3.addTune("How To Save A Life", "The Fray", 350, Genre.POP);
        mp3.addTune("Baby", "Justin Bieber", 350, Genre.POP);
    }

    /*
    *Allows the user to view all songs available in the system and play a song. Checks if system is switched
    * on or off
    * */
    private static void selectFromFullList(){
        if(running) {
            if(mp3.getTuneInfo() != null) {
                String[] songs = mp3.getTuneInfo();
                int len = songs.length;

                //format the data to display properly
                String[] display = new String[len];
                int index = 0;
                for (String song : songs) {
                    String artist = song.toString();
                    String[] tunes = artist.split(",");
                    display[index] = tunes[1] + " By" + tunes[2] + ", " +tunes[0];
                    index++;
                }
                bubbleSortAsc(display);
                System.out.println();
                Menu songMenu = new Menu("Select a Song", display);
                int choice = songMenu.getUserChoice();
                int tuneId = retrieveTuneID(display, choice);

                System.out.println(mp3.play(tuneId - 1));
                System.out.println();
            }else{
                System.out.println("MP3 Player has no songs");
            }

        }else{
            System.out.println("System is switched off");
        }
    }
    /*
    * Allows the user to select a song by viewing the artist first
    * */
    private static void selectTuneByArtist(){
            if (running) {
                String songs[] = mp3.getTuneInfo();
                int len = songs.length;

                String[] artists = new String[len];
                int index = 0;

                for (int i = 0; i < artists.length; i++) {
                    String artist = songs[i].toString();
                    String[] tunes = artist.split(",");
                    artists[index] = tunes[2];
                    index++;
                }

                //sorts the artists in ascending order a-z
                bubbleSortAsc(artists);
                len = removeDuplicateElements(artists, len);
                for (int i = 0; i < len; i++) {
                    // print artists array for every index less than length of len
                    System.out.println((i + 1) + "." + artists[i]);
                }
                System.out.println();
                try {
                    System.out.println("Enter The Artist You Would Like To View");
                    Scanner artistChoice = new Scanner(System.in);
                    String choice = artistChoice.nextLine();

                    //format the data to display properly
                    String[] temp = mp3.getTuneInfo(choice);
                    String[] display = new String[temp.length];
                    index = 0;
                    for (String song : temp) {
                        String artist = song.toString();
                        String[] tunes = artist.split(",");
                        display[index] = tunes[1] + " By" + tunes[2] + " - ID" + ", " +tunes[0];
                        index++;
                    }
                    bubbleSortAsc(display);
                    System.out.println();


                    Menu artistMenu = new Menu("Songs Sorted By Artist", display);
                    int songChoice = artistMenu.getUserChoice();
                    int tuneId = retrieveTuneID(display, songChoice);

                    System.out.println(mp3.play(tuneId - 1));
                    System.out.println();
                } catch (NullPointerException ex) {
                    System.out.println("Invalid choice");
                }
            } else {
                System.out.println("MP3 player is switched off");
            }
        }


    /**
     *selects tunes by genre
     */
    private static void selectTuneByGenre() {
        if (running) {
            if(mp3.getTuneInfo() != null) {
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

                bubbleSortAsc(genres);
                len = removeDuplicateElements(genres, len);

                int count = 1;
                for (int i = 0; i < len; i++) {
                    System.out.println(count + ". " + genres[i]);
                    count++;
                }
                System.out.println();
                Genre genre;
                //validation
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
                } while (!ok);
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
                //format the data to display properly
                String[] temp = mp3.getTuneInfo(genre);
                String[] display = new String[temp.length];
                index = 0;
                for (String song : temp) {
                    String artist = song.toString();
                    String[] tunes = artist.split(",");
                    display[index] = tunes[1] + " By" + tunes[2] + " ID, " +tunes[0];
                    index++;
                }
                bubbleSortAsc(display);
                System.out.println();

                part02.Menu genreMenu = new part02.Menu("Songs Sorted By Genre", display);
                int choice = genreMenu.getUserChoice();
                int tuneId = retrieveTuneID(display, choice);

                System.out.println(mp3.play(tuneId - 1));
                System.out.println();
            }else {
                System.out.println("MP3 Player has no songs");
            }

        } else {
            System.out.println("System is switched off");
        }
    }

    /**
     * displays the top 10 songs
     */
    private static void displayTopTen(){
        if(running){
            if(mp3.getTuneInfo() != null )  {
                if(mp3.getTuneInfo().length >= 10) {
                    String[] songs = mp3.getTuneInfo();
                    String[] songPlays = new String[songs.length];

                    for (int i = 0; i < songs.length; i++) {
                        String[] tunes = songs[i].split(",");

                        songPlays[i] = tunes[5] + " Plays - " + tunes[1] + " By" + tunes[2];
                    }
                    //sorts song plays in descending order
                    bubbleSortDesc(songPlays);

                    String title = "Top Ten Songs";
                    System.out.println(title);
                    for (int i = 0; i < title.length(); i++) {
                        System.out.print("+");
                    }
                    System.out.println();


                    for (int i = 0; i < 10; i++) {
                        System.out.println(songPlays[i]);
                    }
                }else{
                    System.out.println("Not enough songs to display top 10");
                }
            }else{
                System.out.println("MP3 Player is empty");
            }

        }else {
            System.out.println("System is switched off");
        }
     }

    /**
     * retrieves the tune ID and allows a tune to be played by index as opposed to ID
     * @param data array of songs
     * @param choice the user choice
     * @return the tune ID
     */
     private static int retrieveTuneID(String[] data, int choice){
        String[] ID = data[choice-1].split(", ");
        String tuneID = ID[1];
        int tuneId = Integer.parseInt(tuneID);
        return tuneId;
     }

    //adds a new tune to the system
    private static void addNewTune() {
        if (running) {
            Scanner tune = new Scanner(System.in);
            String title = "";
            String artist = "";
            int duration = 0;
            boolean ok = false;
            Genre genre;
            while(title.length() < 1){
            System.out.println("Enter the song title");
            title = tune.nextLine();
            }
            while(artist.length() < 1){
                System.out.println("Enter the artist");
                artist = tune.nextLine();
            }
            do {
                System.out.println("Enter the duration");
                try {
                    duration = input.nextInt();
                    if (duration > 0) {
                        ok = true;
                    } else {
                        System.out.println("Invalid choice");
                    }
                } catch (Exception ex) {
                    System.out.println("Error invalid input.");
                    input.nextLine();
                }
            }while (duration <= 0);
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
     *
     * @param data the array for elements to be removed from
     * @param n the size of array
     * @return
     */
    private static int removeDuplicateElements(String[] data, int n){
        if (n==0 || n==1){
            return n;
        }
        int j = 0;//for next element
        for (int i=0; i < n-1; i++){
            if (!data[i].equalsIgnoreCase(data[i+1])){
                data[j++] = data[i];
            }
        }
        data[j++] = data[n-1];
        return j;
        }



    /**
     *
     * @param data array to be sorted in ascending order
     */
    private static void bubbleSortAsc(String[] data) {
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

    /**
     *
     * @param data array to be sorted in descending order
     */
    private static void bubbleSortDesc(String[] data) {
        boolean sorted = false;
        String temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < data.length - 1; i++) {
                if (data[i].compareTo(data[i+1]) < 0){
                    temp = data[i];
                    data[i] = data[i+1];
                    data[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
}