import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int lastResult;

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int res) {
        this.lastResult = res;
    }

    public int throwDice() {
        int res = ThreadLocalRandom.current().nextInt(1,6+1);
        setLastResult(res);
        return res;
    }

    public Dice () {
        this.lastResult = getLastResult();
    }
}
