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



    @Override
    public boolean switchOff() {
        String[] data = getTuneInfo();
        File csvFile = new File("src\\soundData.csv");
        PrintWriter out = null;
        try {
            out = new PrintWriter(csvFile);
            out.println("ID, Title, Artist, Duration, Genre, Play Count");
            for(int i =0; i < getTuneInfo().length; i++){
                out.println(data[i] + ", " );
            }
            System.out.println("File written");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean switchOn() {
        boolean hasHeader = true;
        String csvFilePath = "src\\soundData.csv";
        Genre genre = null;
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
                System.out.println(iD + ", " + title + ", " + artist +", " + duration);
                addTune(title, artist, duration, Genre.OTHER);
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
     * gets the current cost per credit in the jukebox to update the user on how much a song play will cost
     * @return
     */
    public int getCostPerCredit() {
        return costPerCredit;
    }


}



