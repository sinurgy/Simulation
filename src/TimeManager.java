import java.util.Objects;

public class TimeManager {
    int currentDay;
    int currentTime;
    int currentTurn;

    public TimeManager() {
        currentTurn = 1;
        currentDay = 1;
    }

    public void TakeTurn() {
        if (Main.environment.board[13][13].foodUnits > 0 && Main.environment.queenAnt.lifeSpan > 0) {

            //Bala ant enters colony
            int balaRandom = Main.random.nextInt(99);
            if (balaRandom == 0 || balaRandom == 1 || balaRandom == 2) {
                Main.environment.balaCollection.add(new BalaAnt());
                Main.environment.board[0][0].balaAnts += 1;
            }

            //Queen does something
            Main.environment.queenAnt.eatFood();

            if (currentTurn == 1) {
                Main.environment.queenAnt.hatchAnt();
            }

            //TODO: Scouts do something
            for (int i = 0; i < Main.environment.scoutCollection.size(); ++i) {
                Main.environment.scoutCollection.get(i).move();
                int currentX = Main.environment.scoutCollection.get(i).xLocation;
                int currentY = Main.environment.scoutCollection.get(i).yLocation;
                if(Main.environment.board[currentX][currentY].revealState == false)
                    Main.environment.board[currentX][currentY].revealState = true;

            }



            //TODO: Soldiers do something


            //TODO: Foragers do something


            //TODO: Balas do something


            currentTurn++;

            if (currentTurn == 11) {
                ++currentDay;

                currentTurn = 1;
            }
        }

        else
            Main.environment.queenAnt.mortality = true;
    }

    public void incrementDay() {

    }

    public void decreasePhermone() {

    }

    public void removeDeadAnts() {

    }
}
