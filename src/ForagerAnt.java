import java.util.ArrayList;

public class ForagerAnt extends Ant {
    String primaryMode;
    ArrayList moveHistory;
    static int lastIDUSed = 0;


    public ForagerAnt() {
        lastIDUSed++;
        ID = lastIDUSed;
        primaryMode = "forage";
        moveHistory = new ArrayList();
    }

    public void pickUpFood() {

    }

    public void depositPhermone() {

    }

    public void depositFood() {

    }
}
