public class QueenAnt extends Ant {

    public QueenAnt() {
        ID = 0;
        lifeSpan = 7200;
        mortality = false;
    }

    public void hatchAnt() {
        int antType;

        antType = Main.random.nextInt(4);

        if(antType == 0) {
            SoldierAnt temp = new SoldierAnt();
            Main.environment.board[13][13].soldierAnts.add(temp);
            Main.environment.scoutCollection.add(temp);
        }

        else if(antType == 1) {
            Main.environment.board[13][13].scoutAnts.add(new Ant("scout"));
        }

        else
            Main.environment.board[13][13].foragerAnts.add(new ForagerAnt());
    }

    public void eatFood() {
        Main.environment.board[13][13].foodUnits -= 1;
    }

}
