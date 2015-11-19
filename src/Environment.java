import java.util.ArrayList;

public class Environment {
    Square[][] board = new Square[27][27];
    ArrayList soldierCollection;
    ArrayList foragerCollection;
    ArrayList scoutCollection;
    ArrayList balaCollection;
    QueenAnt queenAnt;


    public Environment() {

        soldierCollection = new ArrayList();
        foragerCollection = new ArrayList();
        scoutCollection = new ArrayList();
        balaCollection = new ArrayList();
        queenAnt = new QueenAnt();

        //Builds the board of squars, determines which squares get food and how much
        for(int i = 0; i < 27; ++i) {
            for (int j = 0; j < 27; ++j) {
                board[i][j] = new Square();
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
            scoutCollection.add(new Ant("scout"));
        }

        //Reveal squares adjacent to the colony
        for (int i = 12; i < 15; ++i) {
            for (int j = 12; j < 15; ++j) {
                board[i][j].revealState = true;
            }
        }


    }
}
