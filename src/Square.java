import java.util.ArrayList;

public class Square {
    boolean revealState;
    boolean queenPresent;
    int balaAnts;
    int friendlyAnts;
    int foodUnits;
    int phermoneUnits;





    public Square() {
        revealState = false;
        balaAnts = 0;
        friendlyAnts = 0;
        foodUnits = 0;
        phermoneUnits = 0;
        queenPresent = false;
    }


    public void removeAnt() {

    }

    public void revealSquare() {
        revealState = true;
    }
}
