import java.util.ArrayList;
import java.util.Random;

public class Ant {

    int ID;
    int lifeSpan;
    double currentAge;
    boolean mortality;
    int xLocation;
    int yLocation;
    static int lastScoutID = 0;

    public Ant () {
        currentAge = 0.0;
        lifeSpan = 360;
    }

    public Ant (String scoutAnt) {
        lastScoutID++;
        ID = lastScoutID;
        currentAge = 0.0;
        lifeSpan = 360;
    }

    public void move() {



        System.out.println();


    }

    public void takeTurn() {


    }

}
