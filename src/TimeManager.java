import java.util.ArrayList;
import java.util.Objects;

public class TimeManager implements SimulationEventListener {
    int currentDay;
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
                Main.environment.board[0][0].nodeView.setBalaCount(Main.environment.balaCollection.size());
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

            currentTurn++;

            if (currentTurn == 11) {
                ++currentDay;
                ageAnts();
                currentTurn = 1;
            }
        }

        else
            Main.environment.queenAnt.mortality = true;
    }



    public void ageAnts() {
        for (int i = 0; i < Main.environment.scoutCollection.size(); ++i) {
            ScoutAnt thisScout = Main.environment.scoutCollection.get(i);
            thisScout.lifeSpan--;
            if (thisScout.lifeSpan == 0)
                Main.environment.scoutCollection.remove(i);
        }

        for (int i = 0; i < Main.environment.foragerCollection.size(); ++i) {
            ForagerAnt thisForager = Main.environment.foragerCollection.get(i);
            thisForager.lifeSpan--;
            if (thisForager.lifeSpan == 0) {
                if (thisForager.forageMode == true)
                    Main.environment.board[thisForager.xLocation][thisForager.yLocation].foodUnits++;
                Main.environment.foragerCollection.remove(i);
            }
        }

        for (int i = 0; i < Main.environment.soldierCollection.size(); ++i) {
            SoldierAnt thisSoldier = Main.environment.soldierCollection.get(i);
            thisSoldier.lifeSpan--;
            if (thisSoldier.lifeSpan == 0)
                Main.environment.soldierCollection.remove(i);
        }

        for (int i = 0; i < Main.environment.balaCollection.size(); ++i) {
            BalaAnt thisBala = Main.environment.balaCollection.get(i);
            thisBala.lifeSpan--;
            if (thisBala.lifeSpan == 0)
                Main.environment.balaCollection.remove(i);
        }

        Main.environment.queenAnt.lifeSpan--;
        if (Main.environment.queenAnt.lifeSpan == 0)
            Main.environment.queenAnt.mortality = true;
    }

    public void removeMurderedAnts () {
        for (int i = 0; i < Main.environment.scoutCollection.size(); ++i) {
            if (Main.environment.scoutCollection.get(i).mortality == true)
                Main.environment.scoutCollection.remove(i);
        }

        for (int i = 0; i < Main.environment.soldierCollection.size(); ++i) {
            if (Main.environment.soldierCollection.get(i).mortality == true)
                Main.environment.soldierCollection.remove(i);
        }

        for (int i = 0; i < Main.environment.foragerCollection.size(); ++i) {
            if (Main.environment.foragerCollection.get(i).mortality == true)
                Main.environment.foragerCollection.remove(i);
        }

        for (int i = 0; i < Main.environment.balaCollection.size(); ++i) {
            if (Main.environment.balaCollection.get(i).mortality == true)
                Main.environment.balaCollection.remove(i);
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
        }
        else if (simEvent.getEventType() == SimulationEvent.SCOUT_TEST_EVENT)
        {
            // set up antSim.simulation for testing the scout ant
        }
        else if (simEvent.getEventType() == SimulationEvent.FORAGER_TEST_EVENT)
        {
            // set up antSim.simulation for testing the forager ant
        }
        else if (simEvent.getEventType() == SimulationEvent.SOLDIER_TEST_EVENT)
        {
            // set up antSim.simulation for testing the soldier ant
        }
        else if (simEvent.getEventType() == SimulationEvent.RUN_EVENT)
        {
            // run the antSim.simulation continuously
            // this should just be a loop where each iteration of the loop calls your takeTurn method, until
            // the simulation is over
        }
        else if (simEvent.getEventType() == SimulationEvent.STEP_EVENT)
        {
            // run the next turn of the antSim.simulation
            // this is your takeTurn method
            TakeTurn();
        }
        else
        {
            // invalid event occurred
        }
    }
}
