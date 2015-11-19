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
            Main.environment.soldierCollection.add(new SoldierAnt());
        }

        else if(antType == 1) {
            Main.environment.scoutCollection.add(new Ant("scout"));
        }

        else
            Main.environment.foragerCollection.add(new ForagerAnt());
    }

    public void eatFood() {
        Main.environment.board[13][13].foodUnits -= 1;
    }
}
