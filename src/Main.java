import java.util.Random;

public class Main {

    public static Random random = new Random();

    public static Environment board = new Environment();

    public static void main(String[] args) {

        TimeManager timeManager = new TimeManager();

        while(board.grid[13][13].queentAnt.mortality == false) {
            timeManager.TakeTurn();
            timeManager.TakeTurn();




        }

        System.out.println();





    }


}
