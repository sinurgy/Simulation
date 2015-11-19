public class BalaAnt extends Ant {

    static int lastIDUsed = 0;

    public BalaAnt() {
        lastIDUsed++;
        ID = lastIDUsed;
        currentAge = 0.0;
        lifeSpan = 360;
        xLocation = 0;
        yLocation = 0;
    }

    public void attack() {

    }
}
