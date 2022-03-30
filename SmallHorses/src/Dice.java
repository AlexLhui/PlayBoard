import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.Scene;

import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

public class Dice implements ActionListener{

    private int lastResult;
    private final ImageView image;
    public final int sizeX = 44;
    public final int sizeY = 44;
    public int diceID;
    public TestTuio2 dump;

    public boolean newResult = false;
    public boolean thrown = false;
    public boolean moving = false;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
    //int screenWidth = (int)size.getWidth(); // screenWidth will store the width of the screen
    double screenHeight = size.getHeight(); // screenHeight will store the height of the screen

    Timer timer = new Timer(500,this);

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

    public Dice (TestTuio2 dump, int diceID) {
        this.dump = dump;
        this.diceID = diceID;
        int res = getLastResult();
        this.lastResult = getLastResult();
        this.image = new ImageView(new Image("dice_digits.png", sizeX*6*screenHeight/600, sizeY*screenHeight/600,true,true));
        this.image.setViewport(new Rectangle2D(sizeX*6*screenHeight/600, 0, sizeX*screenHeight/600, sizeY*screenHeight/600)); //Out of the digits => no image
    }

    public ImageView getImage(){
        return image;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!newResult && !thrown) {
            if (!moving) {
                if (dump.objList.get(0).getMotionAccel() != 0) {
                    moving = true;
                }
            }
            else { //dice moving
                if (dump.objList.get(0).getMotionAccel() == 0) { //stopped moving
                    thrown = true;
                }
            }
        }
        else {
            timer.stop();
            moving = false;
            thrown = false;
            double angle = dump.objList.get(0).getAngle(); //Between 0 and -2Pi
            angle = Math.abs(angle) * 360 / (2 * Math.PI); //In degrees between 0 and 360
            //        System.out.println("Angle in degrees : " + angle);
            if (angle == 0 || angle == 360) {
                setLastResult(1);
                this.image.setViewport(new Rectangle2D((0) * (sizeX * screenHeight / 600), 0, sizeY * screenHeight / 600, (int) (sizeY * screenHeight / 600)));
                lastResult = 1;
            }
            //Else return result
            int res = (int) Math.ceil(angle / 60);
            setLastResult(res);
            this.image.setViewport(new Rectangle2D((res - 1) * (sizeX * screenHeight / 600), 0, sizeY * screenHeight / 600, (int) (sizeY * screenHeight / 600)));
            lastResult = res; //Return integer between 1 and 6
            newResult = true;
        }
    }
}
