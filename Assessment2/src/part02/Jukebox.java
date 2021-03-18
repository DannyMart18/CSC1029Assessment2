package part02;

import part01.Genre;
import part01.MP3Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Jukebox extends MP3Player {
    private int credits;
    private int costPerCredit;




    public Jukebox() {
        super();
        this.credits = 0;
        this.costPerCredit = 0;
    }

    /**
     * plays the tune chosen by the user, checks if songs are free or require credits
     * @param tuneID
     * @return
     */
    @Override
    public String play(int tuneID) {
        String str = "";
        if (tuneID < 0) {
            str += "Invalid Tune ID";
            return str;
        } else {
            if (costPerCredit ==0) {
                str += super.play(tuneID);
                return str;
            } else if (credits >= 1 && costPerCredit >= 1) {
                credits -- ;
                str += super.play(tuneID);
                return str;
            } else {
                str += "Unable to play song - not enough credits";
                return str;
            }
        }
    }


    /**
     * writes data to the csv and saves the state of the system
     * @return
     */
    @Override
    public boolean switchOff() {
        String[] data = getTuneInfo();
        String csvFile = "soundData.csv";
        String creditsFile = "credits.csv";
        writeCredits(credits, costPerCredit, creditsFile);
        writeSongs(data, csvFile);

        return true;
    }

    /**
     * reads data from the csv and restores the state of the system
     * restores soundData, credits and 'costPerCredit
     * @return
     */
    @Override
    public boolean switchOn() {
        String csvFilePath = "soundData.csv";
        String creditsFile = "credits.csv";
        boolean hasHeader = true;
        String gen = null;
        Genre genre = null;
        try{
            File creditFile = new File(creditsFile);
            Scanner creditsSc = new Scanner(creditFile);
            if(hasHeader) {
                creditsSc.nextLine();
            }
                while (creditsSc.hasNextLine()) {
                    String unparsedFile = creditsSc.nextLine();
                    String[] parse = unparsedFile.split(",");
                    int credits = Integer.parseInt(parse[0]);
                    setCredits(credits);
                    int costPerCredit = Integer.parseInt(parse[1]);
                    setCostPerCredit(costPerCredit);
                }
            creditsSc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            File myFile = new File(csvFilePath);
            Scanner mySc = new Scanner(myFile);
            if(hasHeader) {
                mySc.nextLine();
            }
            while(mySc.hasNextLine()) {
                String unparsedFile = mySc.nextLine();
                String[] parse = unparsedFile.split(",");
                int iD = Integer.parseInt(parse[0].trim());
                String title = parse[1].trim();
                String artist = parse[2].trim();
                int duration = Integer.parseInt(parse[3].trim());
                gen = parse[4].trim();
                if(gen.equalsIgnoreCase("Rock and Roll")){
                    genre = Genre.ROCK;
                }
                if(gen.equalsIgnoreCase("Easy Listening Pop")){
                    genre = Genre.POP;
                }
                if(gen.equalsIgnoreCase("Classical")){
                    genre = Genre.CLASSICAL;
                }
                if(gen.equalsIgnoreCase("Techno Dance")){
                    genre = Genre.DANCE;
                }
                if(gen.equalsIgnoreCase("Smooth Jazz")){
                    genre = Genre.JAZZ;
                }
                if(gen.equalsIgnoreCase("Unknown Genre")){
                    genre = Genre.OTHER;
                }
                int playCount = Integer.parseInt(parse[5].trim());
                //System.out.println(iD + ", " + title + ", " + artist +", " + duration);
                addTune(title, artist, duration, genre);//, genre);
            }
            mySc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }



    /**
     * inserts a coin into the jukebox and updates the credits balance
     * @param coin value
     * @return
     */
    public int insertCoin(int coin){
        if(costPerCredit ==0){
            return 0;
        }
        int coins = 0 ;
        if(coin == 10 || coin == 20 || coin == 50 || coin == 100 || coin == 200) {
            coins += coin;
            while(coins >= costPerCredit){
                credits++;
                coins = coins - costPerCredit;
            }
            return coin;
        }else{
            return 0;
        }

    }

    /**
     * allows the user to update the cost per credit
     * @param cost the new cost per credit
     */
    public void setCostPerCredit(int cost){
        if(cost >= 0) {
            this.costPerCredit = cost;
        }else{
            this.costPerCredit = costPerCredit;
        }
    }

    /**
     * gets the amount of credits in the jukebox to update the user on how much they have available.
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * sets the credits upon restoring the state of the system
     * @param credits
     */
    private void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * gets the current cost per credit in the jukebox to update the user on how much a song play will cost
     * @return
     */
    public int getCostPerCredit() {
        return costPerCredit;
    }
    private static void writeCredits(int credits, int costPerCredit, String filepath){
        try {
            PrintWriter myPw = new PrintWriter(filepath);
            myPw.println("Credits, Cost Per Credit");
                myPw.print(credits + ",");
                myPw.print(costPerCredit + "\n");
            myPw.close();


        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }



    }

    /**
     * writes the songs to the csv file
     * @param data String array of soundData
     * @param filepath csv file
     */
    private static void writeSongs(String[] data, String filepath){
        try {
            PrintWriter myPw = new PrintWriter(filepath);
            myPw.println("ID, Title, Artist, Duration, Style, Play Count");
            for (int i = 0; i < data.length; i ++){
                String[] songs = data[i].split(",");
                myPw.print(songs[0] + ",");
                myPw.print(songs[1] + ",");
                myPw.print(songs[2] + ",");
                myPw.print(songs[3] + ",");
                myPw.print(songs[4] + ",");
                myPw.print(songs[5] + "\n");
            }
            myPw.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }


}



