import java.util.ArrayList;
import java.util.Objects;

public class TimeManager implements SimulationEventListener {
    int currentDay;
    int currentTurn;

    public TimeManager() {
        currentTurn = 0;
        currentDay = 1;
    }

    public void TakeTurn() {
        if (Main.environment.board[13][13].foodUnits > 0 && Main.environment.queenAnt.lifeSpan > 0) {

            currentTurn++;

            //Bala ant enters colony
            int balaRandom = Main.random.nextInt(99);
            if (balaRandom == 0 || balaRandom == 1 || balaRandom == 2) {
                Main.environment.balaCollection.add(new BalaAnt());
                Main.environment.board[0][0].balaAnts++;
                Main.environment.board[0][0].nodeView.setBalaCount(Main.environment.board[0][0].balaAnts);
                Main.environment.board[0][0].nodeView.showBalaIcon();
            }

            //Queen takes a turn
            Main.environment.queenAnt.eatFood();
            if (currentTurn == 1) {
                Main.environment.queenAnt.hatchAnt();
            }

            //Scouts take a turn
            for (int i = 0; i < Main.environment.scoutCollection.size(); ++i) {
                Main.environment.scoutCollection.get(i).takeTurn(Main.environment.scoutCollection.get(i));
            }

            //Foragers take a turn
            for (int i = 0; i < Main.environment.foragerCollection.size(); ++i) {
                Main.environment.foragerCollection.get(i).takeTurn(Main.environment.foragerCollection.get(i));
            }

            //Soldiers take a turn
            for (int i = 0; i < Main.environment.soldierCollection.size(); ++i) {
                Main.environment.soldierCollection.get(i).takeTurn(Main.environment.soldierCollection.get(i));
            }

            //Balas take a turn
            for (int i = 0; i < Main.environment.balaCollection.size(); ++i) {
                Main.environment.balaCollection.get(i).takeTurn(Main.environment.balaCollection.get(i));
            }

            //Remove ants killed by attacks
            removeMurderedAnts();



            if (currentTurn == 10) {
                ++currentDay;
                ageAnts();
                halfPheromone();
                currentTurn = 0;
            }
        }

        else
            Main.environment.queenAnt.mortality = true;
    }



    public void ageAnts() {
        for (int i = 0; i < Main.environment.scoutCollection.size(); ++i) {
            ScoutAnt thisScout = Main.environment.scoutCollection.get(i);
            thisScout.lifeSpan--;
            if (thisScout.lifeSpan == 0) {
                Main.environment.scoutCollection.remove(i);
                if (Main.environment.board[thisScout.xLocation][thisScout.yLocation].scoutAnts == 0)
                    Main.environment.board[thisScout.xLocation][thisScout.yLocation].nodeView.hideScoutIcon();
            }
        }

        for (int i = 0; i < Main.environment.foragerCollection.size(); ++i) {
            ForagerAnt thisForager = Main.environment.foragerCollection.get(i);
            thisForager.lifeSpan--;
            if (thisForager.lifeSpan == 0) {
                if (thisForager.forageMode == true)
                    Main.environment.board[thisForager.xLocation][thisForager.yLocation].foodUnits++;
                Main.environment.foragerCollection.remove(i);
                if (Main.environment.board[thisForager.xLocation][thisForager.yLocation].scoutAnts == 0)
                    Main.environment.board[thisForager.xLocation][thisForager.yLocation].nodeView.hideForagerIcon();
            }
        }

        for (int i = 0; i < Main.environment.soldierCollection.size(); ++i) {
            SoldierAnt thisSoldier = Main.environment.soldierCollection.get(i);
            thisSoldier.lifeSpan--;
            if (thisSoldier.lifeSpan == 0) {
                Main.environment.soldierCollection.remove(i);
                if (Main.environment.board[thisSoldier.xLocation][thisSoldier.yLocation].scoutAnts == 0)
                    Main.environment.board[thisSoldier.xLocation][thisSoldier.yLocation].nodeView.hideSoldierIcon();
            }
        }

        for (int i = 0; i < Main.environment.balaCollection.size(); ++i) {
            BalaAnt thisBala = Main.environment.balaCollection.get(i);
            thisBala.lifeSpan--;
            if (thisBala.lifeSpan == 0) {
                Main.environment.balaCollection.remove(i);
                if (Main.environment.board[thisBala.xLocation][thisBala.yLocation].scoutAnts == 0)
                    Main.environment.board[thisBala.xLocation][thisBala.yLocation].nodeView.hideBalaIcon();
            }
        }

        Main.environment.queenAnt.lifeSpan--;
        if (Main.environment.queenAnt.lifeSpan == 0) {
            Main.environment.queenAnt.mortality = true;
            Main.environment.board[13][13].nodeView.hideQueenIcon();
        }
    }

    public void halfPheromone() {
        for (int i = 0; i < 27 ; i++) {
            for (int j = 0; j < 27; j++) {
                if (Main.environment.board[i][j].phermoneUnits > 0) {
                    Main.environment.board[i][j].phermoneUnits = Main.environment.board[i][j].phermoneUnits / 2;
                    Main.environment.board[i][j].nodeView.setPheromoneLevel(Main.environment.board[i][j].phermoneUnits);
                }
            }
        }
    }

    public void removeMurderedAnts () {
        for (int i = 0; i < Main.environment.scoutCollection.size(); ++i) {
            ScoutAnt thisScout = Main.environment.scoutCollection.get(i);
            Square thisSquare = Main.environment.board[thisScout.xLocation][thisScout.yLocation];

            if (thisScout.mortality == true) {
                thisSquare.scoutAnts--;
                thisSquare.nodeView.setScoutCount(thisSquare.scoutAnts);
                Main.environment.scoutCollection.remove(i);
                if (thisSquare.scoutAnts == 0)
                    thisSquare.nodeView.hideScoutIcon();
            }
        }

        for (int i = 0; i < Main.environment.soldierCollection.size(); ++i) {
            SoldierAnt thisSoldier = Main.environment.soldierCollection.get(i);
            Square thisSquare = Main.environment.board[thisSoldier.xLocation][thisSoldier.yLocation];

            if (thisSoldier.mortality == true) {
                thisSquare.soldierAnts--;
                thisSquare.nodeView.setSoldierCount(thisSquare.soldierAnts);
                Main.environment.soldierCollection.remove(i);
                if (thisSquare.soldierAnts == 0)
                    thisSquare.nodeView.hideSoldierIcon();
            }
        }

        for (int i = 0; i < Main.environment.foragerCollection.size(); ++i) {
            ForagerAnt thisForager = Main.environment.foragerCollection.get(i);
            Square thisSquare = Main.environment.board[thisForager.xLocation][thisForager.yLocation];

            if (thisForager.mortality == true) {
                thisSquare.foragerAnts--;
                thisSquare.nodeView.setForagerCount(thisSquare.foragerAnts);
                Main.environment.foragerCollection.remove(i);
                if (thisSquare.foragerAnts == 0)
                    thisSquare.nodeView.hideForagerIcon();
            }
        }

        for (int i = 0; i < Main.environment.balaCollection.size(); ++i) {
            BalaAnt thisBala = Main.environment.balaCollection.get(i);
            Square thisSquare = Main.environment.board[thisBala.xLocation][thisBala.yLocation];

            if (thisBala.mortality == true) {
                thisSquare.balaAnts--;
                thisSquare.nodeView.setBalaCount(thisSquare.balaAnts);
                Main.environment.balaCollection.remove(i);
                if (thisSquare.balaAnts == 0)
                    thisSquare.nodeView.hideBalaIcon();
            }
        }
    }


    @Override
    public void simulationEventOccurred(SimulationEvent simEvent) {

        if (simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT)
        {
            // set up the antSim.simulation for normal operation
        }
        else if (simEvent.getEventType() == SimulationEvent.QUEEN_TEST_EVENT)
        {
            // set up antSim.simulation for testing the queen ant
            currentTurn++;
            Main.environment.queenAnt.eatFood();
            if (currentTurn == 1) {
                Main.environment.queenAnt.hatchAnt();
            }

            if (currentTurn == 10) {
                ++currentDay;
                ageAnts();
                halfPheromone();
                currentTurn = 0;
            }
        }
        else if (simEvent.getEventType() == SimulationEvent.SCOUT_TEST_EVENT)
        {
            // set up antSim.simulation for testing the scout ant
            for (int i = 0; i < Main.environment.scoutCollection.size(); ++i) {
                Main.environment.scoutCollection.get(i).takeTurn(Main.environment.scoutCollection.get(i));
            }

        }
        else if (simEvent.getEventType() == SimulationEvent.FORAGER_TEST_EVENT)
        {
            // set up antSim.simulation for testing the forager ant
            for (int i = 0; i < Main.environment.foragerCollection.size(); ++i) {
                Main.environment.foragerCollection.get(i).takeTurn(Main.environment.foragerCollection.get(i));
            }
        }
        else if (simEvent.getEventType() == SimulationEvent.SOLDIER_TEST_EVENT)
        {
            // set up antSim.simulation for testing the soldier ant
            for (int i = 0; i < Main.environment.soldierCollection.size(); ++i) {
                Main.environment.soldierCollection.get(i).takeTurn(Main.environment.soldierCollection.get(i));
            }
        }
        else if (simEvent.getEventType() == SimulationEvent.RUN_EVENT)
        {
            // run the antSim.simulation continuously
            // this should just be a loop where each iteration of the loop calls your takeTurn method, until
            // the simulation is over
            while (Main.environment.queenAnt.mortality == false) {
                TakeTurn();
            }
        }
        else if (simEvent.getEventType() == SimulationEvent.STEP_EVENT)
        {
            // run the next turn of the antSim.simulation
            // this is your takeTurn method
            TakeTurn();
        }
        else if (simEvent.getEventType() == SimulationEvent.CUSTOM_EVENT)
        {
            //my own debug button yo
            testPhermoneCollection();


        }
        else
        {
            // invalid event occurred
        }
    }

    private void testAdjacentCollection() {

        ArrayList<Square> test = Main.environment.getAdjacentSquares(Main.environment.board[0][13]);
        for (Square element : test) {
            System.out.println(element.myXLocation + ", " + element.myYLocation);
        }

        System.out.println("------------------------");

        ArrayList<Square> test1 = Main.environment.getAdjacentSquares(Main.environment.board[13][26]);
        for (Square element : test1) {
            System.out.println(element.myXLocation + ", " + element.myYLocation);
        }

        System.out.println("------------------------");

        ArrayList<Square> test2 = Main.environment.getAdjacentSquares(Main.environment.board[26][13]);
        for (Square element : test2) {
            System.out.println(element.myXLocation + ", " + element.myYLocation);
        }
    }

    private void testPhermoneCollection() {
        ArrayList<Square> adjacentCollection = Main.environment.getAdjacentSquares(Main.environment.board[13][13]);
        int mostRecentX = 12;
        int mostRecentY = 13;

        System.out.println("------------adjacentCollection---------------");

        for (Square element : adjacentCollection) {
            System.out.println(element.myXLocation + ", " + element.myYLocation);
        }

        System.out.println("------------sanitized adjacentCollection---------------");

        //cleans collection of unrevealed squares and square ant just moved from
        for (int i = 0; i < adjacentCollection.size(); ++i) {
            if (adjacentCollection.get(i).revealState == false || (adjacentCollection.get(i).myXLocation == mostRecentX && adjacentCollection.get(i).myYLocation == mostRecentY))
                adjacentCollection.remove(i);
        }

        ArrayList<Square> highestPheromones = new ArrayList<>();
        highestPheromones.add(adjacentCollection.get(0));
        int currentHighPheromone = adjacentCollection.get(0).phermoneUnits;

        for (int i = 1; i < adjacentCollection.size(); ++i) {
            if (adjacentCollection.get(i).phermoneUnits == currentHighPheromone) {
                highestPheromones.add(adjacentCollection.get(i));
            }

            if (adjacentCollection.get(i).phermoneUnits > currentHighPheromone) {
                highestPheromones.clear();
                currentHighPheromone = adjacentCollection.get(i).phermoneUnits;
                highestPheromones.add(adjacentCollection.get(i));
            }
        }

        System.out.println("------------highestPhermones---------------");

        for (Square element : highestPheromones) {
            System.out.println(element.myXLocation + ", " + element.myYLocation);
        }



    }
}
