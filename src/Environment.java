import java.util.ArrayList;

public class Environment {
    Square[][] grid = new Square[27][27];


    public Environment() {

        for(int i = 0; i < 27; ++i) {
            for (int j = 0; j < 27; ++j) {
                grid[i][j] = new Square();
                if (Main.random.nextInt(4) == 0) {
                    grid[i][j].foodUnits = Main.random.nextInt(501) + 500;
                }

            }
        }

        Square queenSQ = grid[13][13];

        queenSQ.revealState = true;
        queenSQ.queentAnt = new QueenAnt();
        queenSQ.queenPresent = true;
        queenSQ.foodUnits = 1000;

        for (int i = 0; i < 10; ++i) {
            queenSQ.soldierAnts.add(new SoldierAnt());
        }

        for (int i = 0; i < 50; ++i) {
            queenSQ.foragerAnts.add(new ForagerAnt());
        }

        for (int i = 0; i < 4; ++i) {
            queenSQ.scoutAnts.add(new Ant("Scout"));
        }

        for (int i = 12; i < 15; ++i) {
            for (int j = 12; j < 15; ++j) {
                grid[i][j].revealState = true;
            }
        }


    }
}
