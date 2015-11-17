import java.util.ArrayList;

public class Square {
    boolean revealState;
    boolean queenPresent;
    int enemyAnts;
    int friendlyAnts;
    int foodUnits;
    int phermoneUnits;
    ArrayList soldierAnts;
    ArrayList balaAnts;
    ArrayList foragerAnts;
    ArrayList scoutAnts;
    QueenAnt queentAnt;


    public Square() {
        revealState = false;
        enemyAnts = 0;
        friendlyAnts = 0;
        foodUnits = 0;
        phermoneUnits = 0;
        queenPresent = false;
        soldierAnts = new ArrayList();
        foragerAnts = new ArrayList();
        scoutAnts = new ArrayList();
    }


    public void removeAnt() {

    }

    public void revealSquare() {
        revealState = true;
    }
}
