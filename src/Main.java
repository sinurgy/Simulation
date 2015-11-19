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

        System.out.println();





    }


}
