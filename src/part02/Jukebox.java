package part02;

import part01.MP3Player;

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
        if(costPerCredit == 0){
            str += super.play(tuneID);
            return str;
        }
        else if(credits >= costPerCredit) {
            credits -= costPerCredit;
            str += super.play(tuneID);
            return str;
        }else{
            str += "Unable to play song - not enough credits";
            return str;
        }

    }

    public boolean switchOff() {
        return true;
    }


    public boolean switchOn() {
        return true;
    }

    public int insertCoin(int coin){
        if(coin == 10 || coin == 20 || coin == 50 || coin == 100 || coin == 200) {
            credits += coin;
            return coin;
        }else{
            return 0;
        }

    }

    public void setCostPerCredit(int cost){
        if(cost >= 0) {
            this.costPerCredit = cost;
        }else{
            this.costPerCredit = costPerCredit;
        }
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if(credits > 0){
            this.credits = credits;
        }else{
            this.credits = 0;
        }

    }

    public int getCostPerCredit() {
        return costPerCredit;
    }
}



