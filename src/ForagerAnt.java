import java.util.ArrayList;
import java.util.Stack;

public class ForagerAnt extends Ant {
    boolean forageMode;
    Stack<MoveObj> moveHistory = new Stack<MoveObj>();
    static int lastIDUSed = 0;


    public ForagerAnt() {
        lastIDUSed++;
        ID = lastIDUSed;
        forageMode = true;
        currentAge = 0.0;
        lifeSpan = 360;
        xLocation = 13;
        yLocation = 13;
        MoveObj initialLocation = new MoveObj(xLocation, yLocation);
        moveHistory.push(initialLocation);
    }

    public void move(ForagerAnt thisAnt) {
        Square thisSquare = Main.environment.board[thisAnt.xLocation][thisAnt.yLocation];

        //Forage movement
        if (thisAnt.forageMode == true) {

            ArrayList<Square> adjacentCollection = Main.environment.getAdjacentSquares(thisSquare);
            ArrayList<Square> highestPheromones = createPheromoneCollection(adjacentCollection);
            MoveObj nextLocation;

            //Moving this ant
            if (highestPheromones.size() == 1) {
                thisAnt.xLocation = highestPheromones.get(0).myXLocation;
                thisAnt.yLocation = highestPheromones.get(0).myYLocation;
            }

            else {
                thisAnt.xLocation = highestPheromones.get(Main.random.nextInt(highestPheromones.size())).myXLocation;
                thisAnt.yLocation = highestPheromones.get(Main.random.nextInt(highestPheromones.size())).myYLocation;
            }

            //Adding to move history
            nextLocation = new MoveObj(thisAnt.xLocation, thisAnt.yLocation);
            moveHistory.push(nextLocation);

            //Update thisSquare
            thisSquare = Main.environment.board[thisAnt.xLocation][thisAnt.yLocation];

            //Pick up food
            if (thisSquare.myXLocation != 13 && thisSquare.myYLocation != 13 && thisSquare.foodUnits > 0)
                pickUpFood(thisAnt, thisSquare);
        }

        //Return to nest movement
        else {
            MoveObj lastMove = moveHistory.pop();

            if (moveHistory.size() == 0)
                depositFood(thisAnt, lastMove);

            else {
                thisAnt.xLocation = lastMove.xLocation;
                thisAnt.yLocation = lastMove.yLocation;
                Main.environment.board[thisAnt.xLocation][thisAnt.yLocation].phermoneUnits += 10;
            }
        }
    }

    private ArrayList<Square> createPheromoneCollection(ArrayList<Square> adjacentCollection) {
        ArrayList<Square> highestPheromones = new ArrayList<>();
        int currentHighPheromone = 0;

        for (int i = 0; i < adjacentCollection.size(); ++i) {
            if (adjacentCollection.get(i).phermoneUnits >= currentHighPheromone && adjacentCollection.get(i).revealState == true) {
                currentHighPheromone = adjacentCollection.get(i).phermoneUnits;
                highestPheromones.add(adjacentCollection.get(i));
            }
        }

        return highestPheromones;
    }

    private void pickUpFood(ForagerAnt thisAnt, Square thisSquare) {
        thisSquare.foodUnits--;
        thisSquare.phermoneUnits += 10;
        thisAnt.forageMode = false;
        moveHistory.pop();
    }

    private void depositFood (ForagerAnt thisAnt, MoveObj lastMove) {
        Main.environment.board[lastMove.xLocation][lastMove.yLocation].foodUnits++;
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


}
