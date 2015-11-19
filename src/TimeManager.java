

public class TimeManager {
    int currentDay;
    int currentTime;
    int currentTurn;

    public TimeManager() {
        currentTurn = 1;
        currentDay = 1;
    }

    public void TakeTurn() {
        if (Main.environment.board[13][13].foodUnits > 0 && Main.environment.board[13][13].queentAnt.lifeSpan > 0) {

            //Queen does something
            Main.environment.board[13][13].queentAnt.eatFood();

            if (currentTurn == 1) {
                Main.environment.board[13][13].queentAnt.hatchAnt();
            }

            //TODO: Scout does something


            //TODO: Soldier does something


            //TODO: Forager does something


            //TODO: Bala does something

            currentTurn++;

            if (currentTurn == 11) {
                ++currentDay;

                currentTurn = 1;
            }
        }

        else
            Main.environment.board[13][13].queentAnt.mortality = true;
    }

    public void incrementDay() {

    }

    public void decreasePhermone() {

    }

    public void removeDeadAnts() {

    }
}
