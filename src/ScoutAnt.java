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

    public void move(ScoutAnt thisAnt) {
        int xRandom;
        int yRandom;

        if (thisAnt.xLocation == 0)
            xRandom = Main.random.nextInt(2);

        else if (thisAnt.xLocation == 26)
            xRandom = Main.random.nextInt(2) - 1;

        else
            xRandom = Main.random.nextInt(3) - 1;



        if (thisAnt.yLocation == 0)
            yRandom = Main.random.nextInt(2);

        else if (thisAnt.yLocation == 26)
            yRandom = Main.random.nextInt(2) - 1;

        else
            yRandom = Main.random.nextInt(3) - 1;


        if(xRandom == 0 && yRandom == 0)
            move();  //holy crap I just did recursion without being forced to!!! haha

        else {
            this.xLocation += xRandom;
            this.yLocation += yRandom;
        }
    }
}
