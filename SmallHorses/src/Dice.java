import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int lastResult = 1;
    private final ImageView image;
    public boolean thrown = false;
    public final int sizeX = 44;
    public final int sizeY = 44;

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int res) {
        this.lastResult = res;
    }

    public int throwDice() {
        int res = ThreadLocalRandom.current().nextInt(1,6+1);
        setLastResult(res);
        this.image.setViewport(new Rectangle2D((res-1)*sizeX, 0, sizeX, sizeY));
        return res;
    }

    public Dice () {
        int res = getLastResult();
        this.lastResult = getLastResult();
        this.image = new ImageView(new Image("dice_digits.png"));
        this.image.setViewport(new Rectangle2D(6*sizeX, 0, sizeX, sizeY));
    }

    public ImageView getImage(){
        return image;
    }
}
