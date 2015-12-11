import java.util.ArrayList;
import java.util.Stack;

public class ForagerAnt extends Ant {
    boolean forageMode;
    Stack<MoveObj> moveHistory = new Stack<MoveObj>();
    static int lastIDUSed = 0;
    MoveObj mostRecentLocation;


    public ForagerAnt() {
        lastIDUSed++;
        ID = lastIDUSed;
        forageMode = true;
        lifeSpan = 360;
        xLocation = 13;
        yLocation = 13;
        mostRecentLocation = new MoveObj(xLocation, yLocation);
        MoveObj initialLocation = new MoveObj(xLocation, yLocation);
        moveHistory.push(initialLocation);
    }

    public void takeTurn(ForagerAnt thisForager) {
        Square thisSquare = Main.environment.board[thisForager.xLocation][thisForager.yLocation];
        thisSquare.foragerAnts--;
        mostRecentLocation.xLocation = thisSquare.myXLocation;
        mostRecentLocation.yLocation = thisSquare.myYLocation;
        thisSquare.nodeView.setForagerCount(thisSquare.foragerAnts);
        if (thisSquare.foragerAnts == 0)
            thisSquare.nodeView.hideForagerIcon();

        //Forage movement
        if (thisForager.forageMode == true) {

            ArrayList<Square> adjacentCollection = Main.environment.getAdjacentSquares(thisSquare);
            ArrayList<Square> highestPheromones = createPheromoneCollection(adjacentCollection, mostRecentLocation);
            MoveObj nextLocation;

            //Moving this ant
            if (highestPheromones.size() == 1) {
                thisSquare = highestPheromones.get(0);
                thisForager.xLocation = thisSquare.myXLocation;
                thisForager.yLocation = thisSquare.myYLocation;
                thisSquare.foragerAnts++;
                thisSquare.nodeView.setForagerCount(thisSquare.foragerAnts);
                thisSquare.nodeView.showForagerIcon();
            }

            else {
                thisSquare = highestPheromones.get(Main.random.nextInt(highestPheromones.size()));
                thisForager.xLocation = thisSquare.myXLocation;
                thisForager.yLocation = thisSquare.myYLocation;
                thisSquare.foragerAnts++;
                thisSquare.nodeView.setForagerCount(thisSquare.foragerAnts);
                thisSquare.nodeView.showForagerIcon();
            }

            //Adding to move history
            nextLocation = new MoveObj(thisForager.xLocation, thisForager.yLocation);
            moveHistory.push(nextLocation);

            //Update thisSquare
            thisSquare = Main.environment.board[thisForager.xLocation][thisForager.yLocation];

            //Pick up food
            if (thisSquare.myXLocation != 13 && thisSquare.myYLocation != 13 && thisSquare.foodUnits > 0)
                pickUpFood(thisForager, thisSquare);
        }

        //Return to nest movement
        else {
            MoveObj lastMove = moveHistory.pop();

            if (moveHistory.size() == 0)
                depositFood(thisForager, lastMove);

            else {
                thisForager.xLocation = lastMove.xLocation;
                thisForager.yLocation = lastMove.yLocation;
                thisSquare = Main.environment.board[lastMove.xLocation][lastMove.yLocation];
                thisSquare.foragerAnts++;
                thisSquare.nodeView.setForagerCount(thisSquare.foragerAnts);
                thisSquare.nodeView.showForagerIcon();
                if (thisSquare.phermoneUnits < 1000 && (thisSquare.myXLocation != 13 && thisSquare.myYLocation !=13)) {
                    thisSquare.phermoneUnits += 10;
                    thisSquare.nodeView.setPheromoneLevel(thisSquare.phermoneUnits);
                }
            }
        }
    }



    private void pickUpFood(ForagerAnt thisAnt, Square thisSquare) {
        thisSquare.foodUnits--;
        thisSquare.nodeView.setFoodAmount(thisSquare.foodUnits);
        if (thisSquare.phermoneUnits < 1000) {
            thisSquare.phermoneUnits += 10;
            thisSquare.nodeView.setPheromoneLevel(thisSquare.phermoneUnits);
        }
        thisAnt.forageMode = false;
        moveHistory.pop();
    }

    private void depositFood (ForagerAnt thisAnt, MoveObj lastMove) {
        thisAnt.xLocation = 13;
        thisAnt.yLocation = 13;
        Main.environment.board[lastMove.xLocation][lastMove.yLocation].foodUnits++;
        Main.environment.board[lastMove.xLocation][lastMove.yLocation].nodeView.setFoodAmount(Main.environment.board[lastMove.xLocation][lastMove.yLocation].foodUnits);
        Main.environment.board[lastMove.xLocation][lastMove.yLocation].foragerAnts++;
        Main.environment.board[lastMove.xLocation][lastMove.yLocation].nodeView.setForagerCount(Main.environment.board[lastMove.xLocation][lastMove.yLocation].foragerAnts);
        Main.environment.board[lastMove.xLocation][lastMove.yLocation].nodeView.showForagerIcon();
        thisAnt.forageMode = true;
        moveHistory.push(lastMove);
    }


    private class MoveObj {
        int xLocation;
        int yLocation;

        public MoveObj(int X, int Y) {
            xLocation = X;
            yLocation = Y;
        }
    }

    private ArrayList<Square> createPheromoneCollection(ArrayList<Square> adjacentCollection, MoveObj mostRecentLocation) {

        //cleans collection of unrevealed squares and square ant just moved from
        for (int i = 0; i < adjacentCollection.size(); ++i) {
            if (adjacentCollection.get(i).revealState == false || (adjacentCollection.get(i).myXLocation == mostRecentLocation.xLocation && adjacentCollection.get(i).myYLocation == mostRecentLocation.yLocation))
                adjacentCollection.remove(i);
        }

        ArrayList<Square> highestPheromones = new ArrayList<>();
        highestPheromones.add(adjacentCollection.get(0));
        int currentHighPheromone = adjacentCollection.get(0).phermoneUnits;

        for (int i = 1; i < adjacentCollection.size(); ++i) {
            if (adjacentCollection.get(i).phermoneUnits == currentHighPheromone) {
                highestPheromones.add(adjacentCollection.get(i));
            }

            if (adjacentCollection.get(i).phermoneUnits > currentHighPheromone) {
                highestPheromones.clear();
                currentHighPheromone = adjacentCollection.get(i).phermoneUnits;
                highestPheromones.add(adjacentCollection.get(i));
            }
        }

        return highestPheromones;
    }


}
