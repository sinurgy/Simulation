import java.util.ArrayList;

public class SoldierAnt extends Ant {
    String primaryMode;
    static int lastIDUsed = 0;


    public SoldierAnt() {
        lastIDUsed++;
        ID = lastIDUsed;
        primaryMode = "scout";
    }

    public void attack() {

    }

}

