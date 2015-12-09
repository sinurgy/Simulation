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
        currentAge = 1;
        lifeSpan = 360;
        xLocation = 13;
        yLocation = 13;
        MoveObj initialLocation = new MoveObj(xLocation, yLocation);
        moveHistory.push(initialLocation);
    }

    public void takeTurn(ForagerAnt thisForager) {
        Square thisSquare = Main.environment.board[thisForager.xLocation][thisForager.yLocation];

        //Forage movement
        if (thisForager.forageMode == true) {

            ArrayList<Square> adjacentCollection = Main.environment.getAdjacentSquares(thisSquare);
            ArrayList<Square> highestPheromones = createPheromoneCollection(adjacentCollection);
            MoveObj nextLocation;

            //Moving this ant
            if (highestPheromones.size() == 1) {
                thisSquare = highestPheromones.get(0);
                thisForager.xLocation = thisSquare.myXLocation;
                thisForager.yLocation = thisSquare.myYLocation;
            }

            else {
                thisSquare = highestPheromones.get(Main.random.nextInt(highestPheromones.size()));
                thisForager.xLocation = thisSquare.myXLocation;
                thisForager.yLocation = thisSquare.myYLocation;
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
                if (Main.environment.board[thisForager.xLocation][thisForager.yLocation].phermoneUnits < 1000)
                    Main.environment.board[thisForager.xLocation][thisForager.yLocation].phermoneUnits += 10;
            }
        }
    }



    private void pickUpFood(ForagerAnt thisAnt, Square thisSquare) {
        thisSquare.foodUnits--;
        if (thisSquare.phermoneUnits < 1000)
            thisSquare.phermoneUnits += 10;
        thisAnt.forageMode = false;
        moveHistory.pop();
    }

    private void depositFood (ForagerAnt thisAnt, MoveObj lastMove) {
        thisAnt.xLocation = 13;
        thisAnt.yLocation = 13;
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


}
