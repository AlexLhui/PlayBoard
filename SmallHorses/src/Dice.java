import javafx.scene.image.ImageView;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int lastResult;
    private ImageView image;
    public boolean thrown = false;

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
