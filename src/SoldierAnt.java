import java.util.ArrayList;

public class SoldierAnt extends Ant {
    static int lastIDUsed = 0;

    public SoldierAnt() {
        lastIDUsed++;
        ID = lastIDUsed;
        lifeSpan = 360;
        xLocation = 13;
        yLocation = 13;
    }

    public void takeTurn(SoldierAnt thisSoldier) {
        Square thisSquare = Main.environment.board[thisSoldier.xLocation][thisSoldier.yLocation];

        if (thisSquare.balaAnts > 0)
            attack(thisSquare);

        else {
            thisSquare.soldierAnts--;
            thisSquare.nodeView.setSoldierCount(thisSquare.soldierAnts);
            if (thisSquare.soldierAnts == 0)
                thisSquare.nodeView.hideSoldierIcon();

            ArrayList<Square> adjacentCollection = Main.environment.getAdjacentSquares(thisSquare);

            for (int i = adjacentCollection.size() - 1; i >= 0; i--) {
                if (adjacentCollection.get(i).revealState == false)
                    adjacentCollection.remove(i);
            }

            ArrayList<Square> balaLocations = createBalaCollection(adjacentCollection);

            //Moving this ant
            if (balaLocations.size() == 0) {
                thisSquare = adjacentCollection.get(Main.random.nextInt(adjacentCollection.size()));
                thisSquare.soldierAnts++;
                thisSquare.nodeView.setSoldierCount(thisSquare.soldierAnts);
                thisSoldier.xLocation = thisSquare.myXLocation;
                thisSoldier.yLocation = thisSquare.myYLocation;
                thisSquare.nodeView.showSoldierIcon();
            }
            else {
                thisSquare = balaLocations.get(Main.random.nextInt(balaLocations.size()));
                thisSquare.soldierAnts++;
                thisSquare.nodeView.setSoldierCount(thisSquare.soldierAnts);
                thisSoldier.xLocation = thisSquare.myXLocation;
                thisSoldier.yLocation = thisSquare.myYLocation;
                thisSquare.nodeView.showSoldierIcon();
            }
        }
    }

    private void attack(Square thisSquare) {
        ArrayList<BalaAnt> attackableCollection = new ArrayList<>();

        for (int i = 0; i < Main.environment.balaCollection.size() ; i++) {
            if (Main.environment.balaCollection.get(i).xLocation == thisSquare.myXLocation && Main.environment.balaCollection.get(i).yLocation == thisSquare.myYLocation)
                attackableCollection.add(Main.environment.balaCollection.get(i));
        }

        if (Main.random.nextInt(100) % 2 == 1)
            attackableCollection.get(0).mortality = true;
    }


    private ArrayList<Square> createBalaCollection(ArrayList<Square> adjacentCollection) {

        ArrayList<Square> balaLocations = new ArrayList<>();

        for (int i = 0; i < adjacentCollection.size(); ++i) {
            if (adjacentCollection.get(i).balaAnts > 0 && adjacentCollection.get(i).revealState == true)
                balaLocations.add(adjacentCollection.get(i));
        }

        return balaLocations;
    }

}

