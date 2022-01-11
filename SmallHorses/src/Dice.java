import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int lastResult;
    private final ImageView image;
    public boolean thrown = false;
    public final int sizeX = 44;
    public final int sizeY = 44;
    public boolean notSetDice = true;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
    //int screenWidth = (int)size.getWidth(); // screenWidth will store the width of the screen
    double screenHeight = size.getHeight(); // screenHeight will store the height of the screen

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int res) {
        this.lastResult = res;
    }

    public int throwDice() {
            int res = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            setLastResult(res);
            this.image.setViewport(new Rectangle2D((res - 1) * (sizeX*screenHeight/600), 0, sizeY*screenHeight/600, (int)(sizeY*screenHeight/600)));
            return res;
    }

    public Dice () {
        int res = getLastResult();
        this.lastResult = getLastResult();
        this.image = new ImageView(new Image("dice_digits.png", sizeX*6*screenHeight/600, sizeY*screenHeight/600,true,true));
        this.image.setViewport(new Rectangle2D(sizeX*6*screenHeight/600, 0, sizeX*screenHeight/600, sizeY*screenHeight/600)); //Out of the digits => no image
    }

    public ImageView getImage(){
        return image;
    }
}
