import java.util.ArrayList;

public class ScoutAnt extends Ant {
    static int lastIDUsed = 0;

    public ScoutAnt() {
        lastIDUsed++;
        ID = lastIDUsed;
        lifeSpan = 360;
        xLocation = 13;
        yLocation = 13;
    }

    public void takeTurn(ScoutAnt thisScout) {
        Square thisSquare = Main.environment.board[thisScout.xLocation][thisScout.yLocation];
        ArrayList<Square> adjacentCollection = Main.environment.getAdjacentSquares(thisSquare);

        thisSquare = adjacentCollection.get(Main.random.nextInt(adjacentCollection.size()));
        thisScout.xLocation = thisSquare.myXLocation;
        thisScout.yLocation = thisSquare.myYLocation;
        thisSquare.revealState = true;

/*      int xRandom;
        int yRandom;

        if (thisScout.xLocation == 0)
            xRandom = Main.random.nextInt(2);

        else if (thisScout.xLocation == 26)
            xRandom = Main.random.nextInt(2) - 1;

        else
            xRandom = Main.random.nextInt(3) - 1;



        if (thisScout.yLocation == 0)
            yRandom = Main.random.nextInt(2);

        else if (thisScout.yLocation == 26)
            yRandom = Main.random.nextInt(2) - 1;

        else
            yRandom = Main.random.nextInt(3) - 1;


        if(xRandom == 0 && yRandom == 0)
            move();  //holy crap I just did recursion without being forced to!!! haha

        else {
            this.xLocation += xRandom;
            this.yLocation += yRandom;
        }
*/

    }
}
