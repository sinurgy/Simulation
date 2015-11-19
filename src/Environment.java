import java.util.ArrayList;

public class Environment {
    Square[][] board = new Square[27][27];
    ArrayList soldierCollection = new ArrayList();
    ArrayList foragerCollection = new ArrayList();
    ArrayList scoutCollection = new ArrayList();
    ArrayList balaCollection = new ArrayList();


    public Environment() {

        for(int i = 0; i < 27; ++i) {
            for (int j = 0; j < 27; ++j) {
                board[i][j] = new Square();
                if (Main.random.nextInt(4) == 0) {
                    board[i][j].foodUnits = Main.random.nextInt(501) + 500;
                }
            }
        }

        Square queenSQ = board[13][13];

        queenSQ.revealState = true;
        queenSQ.queentAnt = new QueenAnt();
        queenSQ.queenPresent = true;
        queenSQ.foodUnits = 1000;

        for (int i = 0; i < 10; ++i) {
            SoldierAnt temp = new SoldierAnt();
            queenSQ.soldierAnts.add(temp);
            soldierCollection.add(temp);
        }

        for (int i = 0; i < 50; ++i) {
            ForagerAnt temp = new ForagerAnt();
            queenSQ.foragerAnts.add(temp);
            foragerCollection.add(temp);
        }

        for (int i = 0; i < 4; ++i) {
            Ant temp = new Ant("scout");
            queenSQ.scoutAnts.add(temp);
            scoutCollection.add(temp);
        }

        for (int i = 12; i < 15; ++i) {
            for (int j = 12; j < 15; ++j) {
                board[i][j].revealState = true;
            }
        }


    }
}
