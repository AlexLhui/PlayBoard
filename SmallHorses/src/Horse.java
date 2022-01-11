import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.*;


public class Horse {

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // getScreenSize() returns the size of the screen in pixels
    double screenWidth = size.getWidth(); // screenWidth will store the width of the screen
    double screenHeight = size.getHeight(); // screenHeight will store the height of the screen

    public enum Etat{STABLE, CIRCUIT, ARRIVED}
    Etat situation;
    private int position;
    private int numberHorse; //From 1 to 4
    private final ImageView image;
    public final int sizeX = 44;
    public final int sizeY = 44;
    public static int offset = 160;
    Color color;

    public int getNumberHorse(){
        return this.numberHorse;
    }
    public int getPosition(){
        return this.position;
    }
    public ImageView getImage(){
        return image;
    }
    public void setNumberHorse(int numbHorse){numberHorse = numbHorse;}

    public void setPosition(int pos){
        position = pos;
    }

    public void setColor(Color couleur){
        color = couleur;
    }

    public void initPosition(Horse chev){
        if(chev.color == Color.BLUE){
            chev.setPosition(17);
            chev.getImage().setX((screenWidth/2) - (sizeX*screenHeight/600));
            chev.getImage().setY((screenHeight/2) +6* (sizeX*screenHeight/600));
        }
        else if(chev.color == Color.RED){
            chev.setPosition(27);
            chev.getImage().setX(33);
            chev.getImage().setY(229);
        }
        else if(chev.color == Color.GREEN){
            chev.setPosition(37);
            chev.getImage().setX(327);
            chev.getImage().setY(33);
        }
        else if(chev.color == Color.YELLOW){
            chev.setPosition(47);
            chev.getImage().setX(523);
            chev.getImage().setY(327);
        }
    }
    public void beenEaten(Horse chev){
        if(chev.color == Color.BLUE){
            chev.setPosition(chev.getNumberHorse());
        }
        else if(chev.color == Color.RED){
            chev.setPosition(chev.getNumberHorse()+4);
        }
        else if(chev.color == Color.GREEN){
            chev.setPosition(chev.getNumberHorse()+8);
        }
        else if(chev.color == Color.YELLOW){
            chev.setPosition(chev.getNumberHorse()+12);
        }
    }

    public Horse(int position, Etat situation, String filename, int N){
        this.position = position;
        this.situation = situation;
        this.image = new ImageView(new Image(filename,(int)(sizeX*6*screenHeight/600),(int)(sizeY*screenHeight/600),true,true));
        this.image.setViewport(new Rectangle2D(0, 0, (int)(sizeX*screenHeight/600), (int)(sizeY*screenHeight/600)));
    }

}
