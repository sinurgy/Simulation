import java.util.Random;

public class Main {

    public static Random random = new Random();

    public static Environment environment = new Environment();

    public static void main(String[] args) {

        TimeManager timeManager = new TimeManager();

        while(environment.queenAnt.mortality == false) {
            timeManager.TakeTurn();
            System.out.println();




        }

        TestScout();








    }

    static void TestScout()  {
        for (int i = 0; i < environment.scoutCollection.size() ; i++) {
            System.out.println(environment.scoutCollection.get(i).xLocation + " , "
                    + environment.scoutCollection.get(i).yLocation + ": "
                    + environment.board[environment.scoutCollection.get(i).xLocation][environment.scoutCollection.get(i).yLocation].revealState);
        }

    }


}
