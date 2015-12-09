import java.util.ArrayList;

public class BalaAnt extends Ant {

    static int lastIDUsed = 0;

    public BalaAnt() {
        lastIDUsed++;
        ID = lastIDUsed;
        currentAge = 1;
        lifeSpan = 360;
        xLocation = 0;
        yLocation = 0;
    }

    public void takeTurn(BalaAnt thisBala) {
        Square thisSquare = Main.environment.board[thisBala.xLocation][thisBala.yLocation];

        if (thisSquare.friendlyAnts > 0)
            attack(thisSquare, thisBala);

        else {
            ArrayList<Square> adjacentCollection = Main.environment.getAdjacentSquares(thisSquare);

            //Moving this ant
            thisSquare = adjacentCollection.get(Main.random.nextInt(adjacentCollection.size()));
            thisBala.xLocation = thisSquare.myXLocation;
            thisBala.yLocation = thisSquare.myYLocation;
        }

    }


    private void attack(Square thisSquare, BalaAnt thisBala) {
        ArrayList<Ant> attackableCollection = new ArrayList<>();

        if (thisSquare.myXLocation == 13 && thisSquare.myYLocation == 13)
            attackableCollection.add(Main.environment.queenAnt);

        else {
            for (int i = 0; i < Main.environment.soldierCollection.size(); i++) {
                if (Main.environment.soldierCollection.get(i).xLocation == thisSquare.myXLocation && Main.environment.soldierCollection.get(i).yLocation == thisSquare.myYLocation)
                    attackableCollection.add(Main.environment.soldierCollection.get(i));
            }

            for (int i = 0; i < Main.environment.scoutCollection.size(); i++) {
                if (Main.environment.scoutCollection.get(i).xLocation == thisSquare.myXLocation && Main.environment.scoutCollection.get(i).yLocation == thisSquare.myYLocation)
                    attackableCollection.add(Main.environment.scoutCollection.get(i));
            }

            for (int i = 0; i < Main.environment.foragerCollection.size(); i++) {
                if (Main.environment.foragerCollection.get(i).xLocation == thisSquare.myXLocation && Main.environment.foragerCollection.get(i).yLocation == thisSquare.myYLocation)
                    attackableCollection.add(Main.environment.foragerCollection.get(i));
            }
        }

        if (Main.random.nextInt(100) % 2 == 1)
            attackableCollection.get(0).mortality = true;
    }
}
