import java.util.ArrayList;

public class Environment {
    Square[][] board = new Square[27][27];
    ArrayList<SoldierAnt> soldierCollection;
    ArrayList<ForagerAnt> foragerCollection;
    ArrayList<ScoutAnt> scoutCollection;
    ArrayList<BalaAnt> balaCollection;

    QueenAnt queenAnt;


    public Environment() {

        soldierCollection = new ArrayList();
        foragerCollection = new ArrayList();
        scoutCollection = new ArrayList();
        balaCollection = new ArrayList();
        queenAnt = new QueenAnt();

        //Builds the board of squares, determines which squares get food and how much
        for(int i = 0; i < 27; ++i) {
            for (int j = 0; j < 27; ++j) {
                board[i][j] = new Square(i, j);
                if (Main.random.nextInt(4) == 0) {
                    board[i][j].foodUnits = Main.random.nextInt(501) + 500;
                }
            }
        }

        Square colonyEntrance = board[13][13];

        colonyEntrance.revealState = true;
        colonyEntrance.queenPresent = true;
        colonyEntrance.foodUnits = 1000;

        //Populate the colony entrance with friendly ants
        for (int i = 0; i < 10; ++i) {
            colonyEntrance.friendlyAnts += 1;
            soldierCollection.add(new SoldierAnt());
        }

        for (int i = 0; i < 50; ++i) {
            colonyEntrance.friendlyAnts += 1;
            foragerCollection.add(new ForagerAnt());
        }

        for (int i = 0; i < 4; ++i) {
            colonyEntrance.friendlyAnts += 1;
            scoutCollection.add(new ScoutAnt());
        }

        //Reveal squares adjacent to the colony
        for (int i = 12; i < 15; ++i) {
            for (int j = 12; j < 15; ++j) {
                board[i][j].revealState = true;
            }
        }
    }

    //Returns a collection of legal adjacent squares; omits current square from collection
     public ArrayList<Square> getAdjacentSquares (Square currentSquare) {

         ArrayList<Square> adjacentCollection = new ArrayList<>();

         int xMin = -1;
         int xMax = 2;
         int yMin = -1;
         int yMax = 2;

         if (currentSquare.myXLocation == 0)
             xMin = 0;

         if (currentSquare.myXLocation == 26)
             xMax = 1;

         if (currentSquare.myYLocation == 0)
             yMin = 0;

         else if (currentSquare.myYLocation == 26)
             yMax = 1;


         boolean centerCheck = false;
         for (int i = xMin; i < xMax; i++) {
             for (int j = yMin; j < yMax; j++) {
                 if (i == 0 && j == 0)
                     centerCheck = true;

                 if (centerCheck == false)
                     adjacentCollection.add(board[i + currentSquare.myXLocation][j + currentSquare.myYLocation]);
                 else
                    centerCheck = false;
             }
         }

         return adjacentCollection;
     }
}
