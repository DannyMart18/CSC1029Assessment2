package part01;

public enum Genre {

    ROCK(0), POP(1), DANCE(2), JAZZ(3), CLASSICAL(4), OTHER(5);

    private String descriptions[] = {"Rock and Roll", "Easy Listening Pop", "Techno Dance",
                                        "Smooth Jazz", "Classical", "Unknown Genre"};

    private int index;
    private Genre(int num){
        index = num;

    }

    //toString method
    public String toString(){
        return descriptions[index];
    }


}
