public class ScoutAnt extends Ant {
    static int lastIDUsed = 0;

    public ScoutAnt() {
        lastIDUsed++;
        ID = lastIDUsed;
        currentAge = 0.0;
        lifeSpan = 360;
        xLocation = 13;
        yLocation = 13;
    }

    public void move(ScoutAnt currentAnt) {


    }
}
