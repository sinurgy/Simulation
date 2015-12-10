import java.util.ArrayList;

public class Square {
    boolean revealState;
    boolean queenPresent;
    int balaAnts;
    int friendlyAnts;
    int soldierAnts;
    int scoutAnts;
    int foragerAnts;
    int foodUnits;
    int phermoneUnits;
    int myXLocation;
    int myYLocation;
    ColonyNodeView nodeView = new ColonyNodeView();



    public Square(int xLocation, int yLocation) {
        revealState = false;
        balaAnts = 0;
        scoutAnts = 0;
        soldierAnts = 0;
        foragerAnts = 0;
        friendlyAnts = 0;
        foodUnits = 0;
        phermoneUnits = 0;
        queenPresent = false;
        myXLocation = xLocation;
        myYLocation = yLocation;
        nodeView.setID(myXLocation + ", " + myYLocation);
        nodeView.setFoodAmount(foodUnits);
        nodeView.setBalaCount(0);
        nodeView.setForagerCount(0);
        nodeView.setScoutCount(0);
        nodeView.setSoldierCount(0);
        nodeView.setQueen(false);
        nodeView.setPheromoneLevel(0);
        nodeView.hideNode();
    }


    public void removeAnt() {

    }

    public void revealSquare() {
        revealState = true;
    }
}
