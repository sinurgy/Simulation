public class QueenAnt extends Ant {

    public QueenAnt() {
        ID = 0;
        lifeSpan = 7200;
    }

    public void hatchAnt() {
        int antType;

        antType = Main.random.nextInt(4);

        if(antType == 0) {
            Main.environment.soldierCollection.add(new SoldierAnt());
            Main.environment.board[13][13].nodeView.setSoldierCount(Main.environment.soldierCollection.size());
        }

        else if(antType == 1) {
            Main.environment.scoutCollection.add(new ScoutAnt());
            Main.environment.board[13][13].nodeView.setScoutCount(Main.environment.scoutCollection.size());
        }

        else {
            Main.environment.foragerCollection.add(new ForagerAnt());
            Main.environment.board[13][13].nodeView.setForagerCount(Main.environment.foragerCollection.size());
        }
    }

    public void eatFood() {
        Square queenSquare = Main.environment.board[13][13];
        queenSquare.foodUnits--;
        queenSquare.nodeView.setFoodAmount(queenSquare.foodUnits);
    }
}
