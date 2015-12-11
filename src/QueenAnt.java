public class QueenAnt extends Ant {

    public QueenAnt() {
        ID = 0;
        lifeSpan = 7200;
    }

    public void hatchAnt() {
        int antType;
        Square colonyEntrance = Main.environment.board[13][13];

        antType = Main.random.nextInt(4);

        if(antType == 0) {
            Main.environment.soldierCollection.add(new SoldierAnt());
            colonyEntrance.soldierAnts++;
            colonyEntrance.nodeView.setSoldierCount(colonyEntrance.soldierAnts);
        }

        else if(antType == 1) {
            Main.environment.scoutCollection.add(new ScoutAnt());
            colonyEntrance.scoutAnts++;
            colonyEntrance.nodeView.setScoutCount(colonyEntrance.scoutAnts);
        }

        else {
            Main.environment.foragerCollection.add(new ForagerAnt());
            colonyEntrance.foragerAnts++;
            colonyEntrance.nodeView.setForagerCount(colonyEntrance.foragerAnts);
        }
    }

    public void eatFood() {
        Square queenSquare = Main.environment.board[13][13];
        queenSquare.foodUnits--;
        queenSquare.nodeView.setFoodAmount(queenSquare.foodUnits);
    }
}
