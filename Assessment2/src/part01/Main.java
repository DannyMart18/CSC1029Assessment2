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
        String menuOptions[] = { "Select From Full List", "Select Tune By Artist", "Select Tune By Genre",
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
    public static void processOption(int option) {
        switch (option) {
            case 1:
                //selectFromFullList
                //System.out.println("Not yet Implemented yet");
                selectFromFullList();
                break;
            case 2:
                //selectTuneByArtist
                selectTuneByArtist();
               // System.out.println("Not yet Implemented yet");
                break;
            case 3:
                //selectTuneByGenre
                System.out.println("Not yet Implemented yet");
                break;
            case 4:
                addNewTune();
               // System.out.println("Not yet Implemented yet");
                break;
            case 5:
                //displayTopTen
                System.out.println("Not yet Implemented yet");
                break;
            case 6:
                //switchOff
                switchOff();
                //System.out.println("Not yet Implemented yet");
                break;
            case 7:
                //switchOn
                switchOn();
               // System.out.println("Not yet Implemented yet");
                break;
            default:
                System.out.println("Invalid Option: " + option + "\n");
        }
    }

    /*
    *Allows the user to view all songs available in the system and play a song. Checks if system is switched
    * on or off
    * */
    public static void selectFromFullList(){
        if(running) {
           // String names[] = mp3.getTuneInfo();
            Menu songMenu = new Menu("Select a Song", mp3.getTuneInfo());
            int choice = songMenu.getUserChoice();
            System.out.println(mp3.getTuneInfo()[choice - 1]);
            System.out.println();
            System.out.println(mp3.play(choice-1));

        }else{
            System.out.println("System is switched off");
        }
    }
    /*
    * Allows the user to select a song by viewing the artist first
    * */
    public static void selectTuneByArtist(){
        if(running){
            String songs[] = mp3.getTuneInfo();
            int len = songs.length;

            //Splits the getTuneInfo and isolates the artists and prints them
            String artists[] = new String[len];
            int index = 0;
            for (int i = 0; i < len; i++){
                String artist = songs[i].toString();
                String[] tunes = artist.split(",");
                artists[index] = tunes[2];
                index++;
            }
            for(int i = 0; i <len; i++){
                System.out.println(artists[i]);
            }
            System.out.println();

            System.out.println("Enter The Artist You Would Like To View");
            String choice = input.nextLine();

            Menu artistMenu = new Menu("Songs", mp3.getTuneInfo(choice));
            System.out.println("Select a song by ID");
            int songChoice = artistMenu.getUserChoiceIndex();
            System.out.println(mp3.play(songChoice - 1));
            System.out.println();
        }else{
            System.out.println("System is switched off");
        }
    }

    //adds a new tune to the system
    public static void addNewTune() {
        if (running) {
            Scanner tune = new Scanner(System.in);
            Genre genre = null;
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
                    System.out.println("Invalid information provided");
                }
        } else {
            System.out.println("System is switched off");

        }
    }
    //switches the system off
    public static void switchOff(){
        if(mp3.switchOn()){
            System.out.println("System has been shut down");
            running = false;
        }else{
            System.out.println("System is already off");
        }
    }
    //switches the system on
    public static void switchOn(){
        if(mp3.switchOff()){
            System.out.println("The system has been switched on");
            running = true;
        }else{
            System.out.println("System is already on");
        }
    }


}
