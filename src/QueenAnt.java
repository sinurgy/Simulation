public class QueenAnt extends Ant {

    public QueenAnt() {
        ID = 0;
        lifeSpan = 7200;
        mortality = false;
    }

    public void hatchAnt() {
        int antType;

        antType = Main.random.nextInt(4);

        if(antType == 0)
            Main.board.grid[13][13].soldierAnts.add(new SoldierAnt());

        else if(antType == 1)
            Main.board.grid[13][13].scoutAnts.add(new Ant("scout"));

        else
            Main.board.grid[13][13].foragerAnts.add(new ForagerAnt());
    }

    public void eatFood() {
        Main.board.grid[13][13].foodUnits -= 1;
    }

}
